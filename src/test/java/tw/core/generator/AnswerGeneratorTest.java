package tw.core.generator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    @Test
    public void should_get_random_answer()throws Exception{

        RandomIntGenerator randomIntGenerator = mock(RandomIntGenerator.class);
        when(randomIntGenerator.generateNums(9,4)).thenReturn("2 5 6 7");
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);

        Answer answer = answerGenerator.generate();
        String[] answerNumStr = answer.toString().split(" ");
        assertThat(answerNumStr.length).isEqualTo(4);
        for (String numStr : answerNumStr) {
            assertThat(Integer.parseInt(numStr)<10).isEqualTo(true);
        }

    }

}

