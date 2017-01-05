package samsanort.clap;

/**
 * Representation of a command line argument that will precede a value.
 * f.i.: "-filename data.csv"
 */
public class ValuedArgument<T> extends Argument {

    private T value;
    private boolean mandatory;
    private ValuedArgumentParser<T> parser;

    /**
     * C'tor.
     * @param name Name of the argument - as it will be provided in the command line.
     * @param description Description of the argument - what is this argument used for.
     * @param mandatory <code>true</code> if the argument is mandatory, <code>false</code> if is optional.
     * @param parser Parser to use to transform its value when fetching it from a String. (used internally when invoking <code>setValueFromString(..)</code>).
     */
    public ValuedArgument(String name, String description, boolean mandatory, ValuedArgumentParser<T> parser) {
        super(name, description);
        this.mandatory = mandatory;
        this.parser = parser;
    }

    public boolean isMandatory(){ return this.mandatory; }
    public T getValue() { return this.value; }

    public void setValueFromString(String stringValue) throws IllegalArgumentException {
        try {
            this.value = this.parser.parse(stringValue);
        }catch(ValuedArgumentParsingException ape) {
            throw new IllegalArgumentException(String.format("Invalid value for argument %s: %s", getName(), ape.getMessage()), ape);
        }
    }

    ValuedArgumentParser<T> getParser() { return this.parser; }
}
