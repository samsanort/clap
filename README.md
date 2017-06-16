# CLAP (Command Line Argument Parser)

Utility library for defining, parsing and validating arguments provided to Java applications via command line.

## Usage example

```
    public static void main(String[] args) {

        // Configure the supported program arguments

        CommandLineArgumentParser parser = new CommandLineArgumentParserImpl("-");

        parser.defineValuedArgument(ValuedArgumentType.String, "-aStringArg", "A mandatory String valued argument.", true);
        parser.defineValuedArgument(ValuedArgumentType.Integer, "-anIntArg", "A mandatory Integer valued argument.", true);
        parser.defineValuedArgument(ValuedArgumentType.String, "-anOptionalStringArg", "An optional String valued argument.", false);
        parser.defineFlag("-aFlag", "A flag. Flags are always optional.");

        // Assuming that the program was invoked with the following arguments:
        // "-aFlag -aStringArg foo -anIntArg 0 -anOptionalStringArg bar"

        Map<String, Argument> parsedArgs = parser.parse(args);

        ((ValuedArgument<String>)parsedArgs.get("-aStringArg")).getValue(); // "foo"
        ((ValuedArgument<Integer>)parsedArgs.get("-anIntArg")).getValue(); // 0
        ((ValuedArgument<String>)parsedArgs.get("-anOptionalStringArg")).getValue(); // "bar"
        parsedArgs.containsKey("-aFlag"); // true

        // If, instead, the program had been invoked with the following arguments:
        // "-aStringArg foo -anOptionalStringArg bar"

        parsedArgs = parser.parse(args);

        // The parser would have thrown an IllegalArgumentException, stating:
        // "Argument -anIntArg is mandatory, but wasn't provided."
    }
```