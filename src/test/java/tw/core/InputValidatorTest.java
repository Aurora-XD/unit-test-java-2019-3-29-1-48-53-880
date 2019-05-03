package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator testObject;

    @Before
    public void get_InputValidator_object(){
        testObject = new InputValidator();
    }

    @Test
    public void check_the_input_numStr_is_valid(){

        String numStr1 = "1 2 3 4";
        assertThat(testObject.validate(numStr1)).isEqualTo(true);

        String numStr2 = "1 3 5 8 1";
        assertThat(testObject.validate(numStr2)).isEqualTo(false);

        String numStr3 = "3 5 6";
        assertThat(testObject.validate(numStr3)).isEqualTo(false);

        String numStr4 = "1 5 13 2";
        assertThat(testObject.validate(numStr4)).isEqualTo(false);

        String numStr5 = "1 5 1 9";
        assertThat(testObject.validate(numStr5)).isEqualTo(false);

    }

}
