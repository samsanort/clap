package samsanort.clap;

/**
 * Factory for type-specific ValuedArgument instance creation.
 */
class ValuedArgumentFactory {

    /**
     * Builds a ValuedArgument instance based on the provided info:
     * @param type Data type of the value.
     * @param name Name of the argument.
     * @param description Description of the argument.
     * @param mandatory true/false to define the argument optionality.
     * @return A new ValuedArgument instance.
     * @throws IllegalArgumentException if the argument type is unsupported.
     */
    static ValuedArgument buildValuedArgument(ValuedArgumentType type, String name, String description, boolean mandatory)
            throws IllegalArgumentException {

        switch(type) {

            case Integer:
                return new ValuedArgument<>(name, description, mandatory, new IntegerValuedArgumentParser());

            case String:
                return new ValuedArgument<>(name, description, mandatory, new StringValuedArgumentParser());

            default:
                throw new IllegalArgumentException(String.format("ValuedArgument type %s not supported.", type));
        }
    }
}
