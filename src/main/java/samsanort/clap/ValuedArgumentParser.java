package samsanort.clap;

/**
 * ValuedArgumentParser interface.
 */
interface ValuedArgumentParser<T> {

    /**
     * Parses the provided String value representation into it's T value.
     * @param value The String representation of the value to parse.
     * @return A T-typed value.
     * @throws ValuedArgumentParsingException If the String cannot be parsed.
     */
    T parse(String value) throws ValuedArgumentParsingException;
}
