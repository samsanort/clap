package samsanort.clap;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CommandLineArgumentParserImplTest {

    private CommandLineArgumentParserImpl testSubject;

    private static final String ARGNAME_FLAG = "-flag";
    private static final String ARGNAME_VARG_STR = "-vargStr";
    private static final String ARGNAME_VARG_INT = "-vargInt";
    private static final String ARGNAME_VARG_OPT = "-vargOpt";

    @Before
    public void init() {
        testSubject = new CommandLineArgumentParserImpl("-");
        testSubject.defineFlag(ARGNAME_FLAG, "A flag.");
        testSubject.defineValuedArgument(ValuedArgumentType.String, ARGNAME_VARG_STR, "A mandatory String valued argument.", true);
        testSubject.defineValuedArgument(ValuedArgumentType.Integer, ARGNAME_VARG_INT, "A mandatory Integer valued argument.", true);
        testSubject.defineValuedArgument(ValuedArgumentType.String, ARGNAME_VARG_OPT, "An optional String valued argument.", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void construct_nullPrefix_throwsIllegalArgumentException() throws Exception {

        // when
        new CommandLineArgumentParserImpl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void construct_emptyPrefix_throwsIllegalArgumentException() throws Exception {

        // when
        new CommandLineArgumentParserImpl("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void construct_blankPrefix_throwsIllegalArgumentException() throws Exception {

        // when
        new CommandLineArgumentParserImpl(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineFlag_nameIsNull_throwsIllegalArgumentException() throws Exception {

        // given
        String name = null;
        String description = "description";

        // when
        testSubject.defineFlag(name, description);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineFlag_descriptionIsNull_throwsIllegalArgumentException() throws Exception {

        // given
        String name = "name";
        String description = null;

        // when
        testSubject.defineFlag(name, description);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineValuedArgument_nameIsNull_throwsIllegalArgumentException() throws Exception {

        // given
        String name = null;
        String description = "description";

        // when
        testSubject.defineValuedArgument(ValuedArgumentType.String, name, description, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineValuedArgument_descriptionIsNull_throwsIllegalArgumentException() throws Exception {

        // given
        String name = "name";
        String description = null;

        // when
        testSubject.defineValuedArgument(ValuedArgumentType.String, name, description, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineValuedArgument_nameWithoutPrefix_throwsIllegalArgumentException() throws Exception {

        // given
        String name = "name";
        String description = "description";

        // when
        testSubject.defineValuedArgument(ValuedArgumentType.String, name, description, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void defineFlag_nameWithoutPrefix_throwsIllegalArgumentException() throws Exception {

        // given
        String name = "name";
        String description = "description";

        // when
        testSubject.defineFlag(name, description);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_undefinedArgument_throwsIllegalArgumentException() throws Exception {

        // given
        String[] undefinedArg = { "-foo" };

        // when
        testSubject.parse(undefinedArg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_valuedArgumentWithoutValue_throwsIllegalArgumentException() throws Exception {

        // given
        String[] noValueArg = { ARGNAME_VARG_STR };

        // when
        testSubject.parse(noValueArg);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_mandatoryArgumentMissing_throwsIllegalArgumentException() throws Exception {

        // given
        String[] oneArgMissing = { ARGNAME_VARG_STR, "vargStr value" };

        // when
        testSubject.parse(oneArgMissing);
    }

    @Test
    public void parse_validArgs_returnsParsedArgs() throws Exception {

        // given
        String vargStrValue = "vargStrValue";
        Integer vargIntValue = 15;
        String vargOptValue = "vargOptValue";
        String[] allArgs = {
                ARGNAME_FLAG,
                ARGNAME_VARG_STR, vargStrValue,
                ARGNAME_VARG_INT, vargIntValue.toString(),
                ARGNAME_VARG_OPT, vargOptValue
        };

        // when
        Map<String, Argument> parsedArgs = testSubject.parse(allArgs);

        // then
        assertThat(parsedArgs.containsKey(ARGNAME_FLAG), is(true));
        assertThat(parsedArgs.containsKey(ARGNAME_VARG_STR), is(true));
        assertThat(parsedArgs.containsKey(ARGNAME_VARG_INT), is(true));
        assertThat(parsedArgs.containsKey(ARGNAME_VARG_OPT), is(true));
        assertValuedArgumentValue((ValuedArgument)parsedArgs.get(ARGNAME_VARG_STR), vargStrValue);
        assertValuedArgumentValue((ValuedArgument)parsedArgs.get(ARGNAME_VARG_INT), vargIntValue);
        assertValuedArgumentValue((ValuedArgument)parsedArgs.get(ARGNAME_VARG_OPT), vargOptValue);
    }

    private static <T> void assertValuedArgumentValue(ValuedArgument varg, T value) {
        assertThat( (T) varg.getValue(), is(equalTo( value )) );
    }
}
