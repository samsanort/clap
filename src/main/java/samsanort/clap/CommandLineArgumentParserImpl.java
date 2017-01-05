package samsanort.clap;

import java.util.HashMap;
import java.util.Map;

public class CommandLineArgumentParserImpl implements CommandLineArgumentParser {

    private Map<String, Argument> definedArguments = new HashMap<>();
    private String argumentPrefix;

    /**
     * C'tor.
     * @param argumentPrefix Prefix that argument names will have.
     *                       f.i.: "-" , for arguments like "-arg1"
     */
    public CommandLineArgumentParserImpl(String argumentPrefix) {

        if(argumentPrefix == null || argumentPrefix.trim().isEmpty())
            throw new IllegalArgumentException("Argument prefix cannot be null, empty nor blank.");

        this.argumentPrefix = argumentPrefix;
    }

    @Override
    public void defineFlag(String name, String description) {
        validateNameAndDescription(name, description);
        this.definedArguments.put(name, new Argument(name, description));
    }

    @Override
    public void defineValuedArgument(ValuedArgumentType type, String name, String description, boolean mandatory) {
        validateNameAndDescription(name, description);
        this.definedArguments.put(name, ValuedArgumentFactory.buildValuedArgument(type, name, description, mandatory));
    }

    @Override
    public Map<String, Argument> parse(String[] args) throws IllegalArgumentException {
        Map<String, Argument> parsedArguments = parseArguments(args);
        checkMandatoryArguments(parsedArguments);
        return parsedArguments;
    }

    private void validateNameAndDescription(String name, String description) {

        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Argument name cannot be empty.");

        if(description == null || description.isEmpty())
            throw new IllegalArgumentException("Argument description cannot be empty.");

        if(!name.startsWith(this.argumentPrefix))
            throw new IllegalArgumentException("Argument name must start with prefix " + this.argumentPrefix);
    }

    private Map<String, Argument> parseArguments(String[] args) {

        Map<String, Argument> parsedArguments = new HashMap<>();

        boolean expectingValue = false;
        String argNameOfExpectedValue = null;
        for(String arg : args) {

            if(arg.startsWith(argumentPrefix)) {

                // this is a flag or a valued arg name,
                // let's validate and register it

                if(expectingValue) throw new IllegalArgumentException(String.format("Found argument %s when expecting value for argument %s.", arg, argNameOfExpectedValue));

                Argument definedArg = definedArguments.get(arg);
                if(definedArg != null) {

                    if(definedArg instanceof ValuedArgument) {
                        expectingValue = true;
                        argNameOfExpectedValue = arg;
                    }

                    parsedArguments.put(arg, definedArg);

                } else {
                    throw new IllegalArgumentException("Tried to parse undefined argument: " + arg);
                }

            } else {

                // this is a valued arg value,
                // let's parse its value

                ((ValuedArgument)parsedArguments.get(argNameOfExpectedValue)).setValueFromString(arg);
                expectingValue = false;
            }
        }

        if(expectingValue) throw new IllegalArgumentException("Missing value for argument " + argNameOfExpectedValue);

        return parsedArguments;
    }

    private void checkMandatoryArguments(Map<String, Argument> parsedArguments) {
        for(Argument definedArgument : definedArguments.values()) {
            if(definedArgument instanceof  ValuedArgument) {
                if(((ValuedArgument) definedArgument).isMandatory()) {
                    if(!parsedArguments.containsKey(definedArgument.getName())) {
                        throw new IllegalArgumentException(
                                String.format(
                                        "Argument %s is mandatory, but wasn't provided.",
                                        definedArgument.getName()));
                    }
                }
            }
        }
    }

}
