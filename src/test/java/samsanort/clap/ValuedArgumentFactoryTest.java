package samsanort.clap;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ValuedArgumentFactoryTest {

    @Test
    public void buildArgument_validType_returnsValuedArgument() throws Exception {

        // given
        ValuedArgumentType argType = ValuedArgumentType.String;
        String argName = "argName";
        String argDescription = "argument description";
        boolean argMandatory = false;

        // when
        ValuedArgument builtValuedArg =
                ValuedArgumentFactory.buildValuedArgument(argType, argName, argDescription, argMandatory);

        // then
        assertThat(builtValuedArg.getName(), is(equalTo(argName)));
        assertThat(builtValuedArg.getDescription(), is(equalTo(argDescription)));
        assertThat(builtValuedArg.isMandatory(), is(equalTo(argMandatory)));
    }

    @Test
    public void buildArgument_stringType_returnsValuedArgumentThatParsesStrings() throws Exception {

        // given
        ValuedArgumentType argType = ValuedArgumentType.String;

        // when
        ValuedArgument builtValuedArg =
                ValuedArgumentFactory.buildValuedArgument(argType, "name", "desc", false);

        // then
        assertThat(builtValuedArg.getParser(), is(instanceOf(StringValuedArgumentParser.class)));
    }

    @Test
    public void buildArgument_integerType_returnsValuedArgumentThatParsesStrings() throws Exception {

        // given
        ValuedArgumentType argType = ValuedArgumentType.Integer;

        // when
        ValuedArgument builtValuedArg =
                ValuedArgumentFactory.buildValuedArgument(argType, "name", "desc", false);

        // then
        assertThat(builtValuedArg.getParser(), is(instanceOf(IntegerValuedArgumentParser.class)));
    }

}
