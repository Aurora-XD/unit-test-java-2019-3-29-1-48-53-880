package tw.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    @Test
    public void get_answer_object(){

        Answer answer = Answer.createAnswer("1 2 3 4");
        assertThat(answer.toString()).isEqualTo("1 2 3 4");

    }

    @Test
    public void check_the_answer_of_input(){

        String answerStr = "1 2 3 4";
        Answer answer = Answer.createAnswer(answerStr);

        Answer inputAnswer1 = new Answer();
        String[] inputStr1 = {"1","2","3","4"};
        inputAnswer1.setNumList(Arrays.asList(inputStr1));
        assertThat(answer.check(inputAnswer1).getValue()).isEqualTo("4A0B");

        Answer inputAnswer2 = new Answer();
        String[] inputStr2 = {"1","5","6","7"};
        inputAnswer2.setNumList(Arrays.asList(inputStr2));
        assertThat(answer.check(inputAnswer2).getValue()).isEqualTo("1A0B");

        Answer inputAnswer3 = new Answer();
        String[] inputStr3 = {"2","4","7","8"};
        inputAnswer3.setNumList(Arrays.asList(inputStr3));
        assertThat(answer.check(inputAnswer3).getValue()).isEqualTo("0A2B");

    }

}