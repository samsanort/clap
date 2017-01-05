package samsanort.clap;

import java.util.Map;

/**
 * Command line argument parser interface.
 *
 * Expected usage:
 * <code>
 * ...
 * commandLineArgumentParser.defineFlag("-flag", "A flag.");
 * commandLineArgumentParser.defineValuedArgument(ValuedArgumentType.String, "-strArg", "A mandatory String valued argument.", true);
 * commandLineArgumentParser.defineValuedArgument(ValuedArgumentType.String, "-strArgOpt", "An optional String valued argument.", false);
 * ...
 * Map<String, Argument> parsedArgs =
 *   commandLineArgumentParser.parse(
 *     new String[] {"-flag","-strArg", "strArg value"});
 * ...
 * </code>
 *
 * This should return:
 * {
 *   "-flag": @Argument{name="-flag"} ,
 *   "-strArg": @ValuedArgument{name="-strArg", value="strArg value"}
 * }
 */
public interface CommandLineArgumentParser {

    /**
     * Define a new flag-like argument to be parsed when invoking <code>parse(..)</code>.
     * f.i.: -verbose
     * @param name Name of the argument - as it will be provided in the command line.
     * @param description Description of the argument - what it is used for.
     */
    void defineFlag(String name, String description);

    /**
     * Define a new valued argument to be parsed when invoking <code>parse(..)</code>.
     * Valued arguments are args that define a value after them.
     * f.i.: -filename data.csv
     * @param type Data type of the value.
     * @param name Name of the argument - as it will be provided in the command line.
     * @param description Description of the argument - what it is used for.
     * @param mandatory <code>true</code> if the argument MUST be provided (present in the args to parse), <code>false</code> if is optional.
     */
    void defineValuedArgument(ValuedArgumentType type, String name, String description, boolean mandatory);

    /**
     * Parses the provided arguments applying the definitions specified previously using
     * <code>defineFlag(..)</code> and/or <code>defineValuedArgument(..)</code>.
     * @param args Command line arguments to parse.
     * @return A map containing the parsed arguments, where:
     * K are the parsed argument names,
     * V are the argument values.
     * Only those arguments defined and found in <code>args</code> should be returned inside the map.
     * @throws IllegalArgumentException
     */
    Map<String, Argument> parse(String[] args) throws IllegalArgumentException;
}
