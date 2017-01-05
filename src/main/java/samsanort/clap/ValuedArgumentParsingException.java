package samsanort.clap;

/**
 * Exception representing a problem occurred when parsing the value of a
 * ValuedArgument.
 */
public class ValuedArgumentParsingException extends Exception {

    /**
     * C'tor.
     *
     * @param message Exception message.
     */
    public ValuedArgumentParsingException(String message) {
        super(message);
    }
}
