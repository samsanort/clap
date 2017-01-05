package samsanort.clap;

/**
 * Parser for valued arguments containing Integer values.
 */
class IntegerValuedArgumentParser implements ValuedArgumentParser<Integer> {

    @Override
    public Integer parse(String value) throws ValuedArgumentParsingException {

        if(value == null) throw new ValuedArgumentParsingException("Value is null");

        try {
            return Integer.parseInt(value);

        } catch(NumberFormatException nfe) {
            throw new ValuedArgumentParsingException(String.format("%s is not a valid integer.", value));
        }
    }
}
