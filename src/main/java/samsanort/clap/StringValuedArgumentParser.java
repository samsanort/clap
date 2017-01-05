package samsanort.clap;

/**
 * Parser for valued arguments containing String values.
 */
class StringValuedArgumentParser implements ValuedArgumentParser<String> {

    @Override
    public String parse(String value) throws ValuedArgumentParsingException {

        if(value == null) throw new ValuedArgumentParsingException("Value is null");

        return value;
    }
}
