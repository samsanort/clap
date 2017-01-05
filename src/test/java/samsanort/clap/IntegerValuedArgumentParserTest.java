package samsanort.clap;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class IntegerValuedArgumentParserTest {

    private IntegerValuedArgumentParser testSubject = new IntegerValuedArgumentParser();

    @Test(expected = ValuedArgumentParsingException.class)
    public void parse_nullValue_throwsValuedArgumentParsingException() throws Exception {

        // when
        testSubject.parse(null);
    }

    @Test(expected = ValuedArgumentParsingException.class)
    public void parse_nonIntegerRepresentationValue_throwsValuedArgumentParsingException() throws Exception {

        // when
        testSubject.parse("not an integer");
    }

    @Test
    public void parse_integerRepresentationValue_returnsInteger() throws Exception {

        // given
        String anIntegerRepresentation = "25";

        // when
        Integer parsedValue = testSubject.parse(anIntegerRepresentation);

        // then
        assertThat(parsedValue.intValue(), is(equalTo( Integer.parseInt(anIntegerRepresentation) )));
    }

}
