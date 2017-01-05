package samsanort.clap;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class StringValuedArgumentParserTest {

    private StringValuedArgumentParser testSubject = new StringValuedArgumentParser();

    @Test(expected = ValuedArgumentParsingException.class)
    public void parse_nullValue_throwsValuedArgumentParsingException() throws Exception {

        // when
        testSubject.parse(null);
    }

    @Test
    public void parse_stringValue_returnsString() throws Exception {

        // given
        String aStringValue = "a string value";

        // when
        String parsedValue = testSubject.parse(aStringValue);

        // then
        assertThat(parsedValue, is(equalTo(aStringValue)));
    }

}
