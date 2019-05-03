package tw.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import tw.core.generator.AnswerGenerator;
import org.mockito.Mock;
import tw.core.model.GuessResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    @Mock
    private AnswerGenerator answerGenerator;
    private Game game;

    @Before
    public void setUp() throws Exception{
        //初始化测试用例中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
        Answer answer = Answer.createAnswer("1 2 3 4");
        when(answerGenerator.generate()).thenReturn(answer);
        game = new Game(answerGenerator);

    }

    @Test
    public void guess_correct_result_when_first_input(){

        Answer inputAnswer1 = new Answer();
        String[] inputStr1 = {"1","2","3","4"};
        inputAnswer1.setNumList(Arrays.asList(inputStr1));

        game.guess(inputAnswer1);

        List<GuessResult> guessResultList = game.guessHistory();

        assertThat(guessResultList.size()).isEqualTo(1);
        assertThat(guessResultList.get(0).getResult()).isEqualTo("4A0B");

        assertThat(game.checkStatus()).isEqualTo("success");
        assertThat(game.checkCoutinue()).isEqualTo(false);
    }

    @Test
    public void guess_correct_result_when_input_more_than_one_time(){

        Answer inputAnswer2 = new Answer();
        String[] inputStr2 = {"1","5","6","7"};
        inputAnswer2.setNumList(Arrays.asList(inputStr2));

        Answer inputAnswer3 = new Answer();
        String[] inputStr3 = {"2","4","7","8"};
        inputAnswer3.setNumList(Arrays.asList(inputStr3));

        Answer inputAnswer4 = new Answer();
        String[] inputStr4 = {"1","2","3","4"};
        inputAnswer4.setNumList(Arrays.asList(inputStr4));

        game.guess(inputAnswer2);
        game.guess(inputAnswer3);
        game.guess(inputAnswer4);

        List<GuessResult> guessResultList = game.guessHistory();

        assertThat(guessResultList.size()).isEqualTo(3);
        assertThat(guessResultList.get(0).getResult()).isEqualTo("1A0B");
        assertThat(guessResultList.get(1).getResult()).isEqualTo("0A2B");
        assertThat(guessResultList.get(2).getResult()).isEqualTo("4A0B");

        assertThat(game.checkStatus()).isEqualTo("success");
        assertThat(game.checkCoutinue()).isEqualTo(false);
    }

    @Test
    public void guess_result_when_input_a_wrong_answer_and_gameStatus_is_continue(){

        Answer inputAnswer5 = new Answer();
        String[] inputStr5 = {"1","5","6","7"};
        inputAnswer5.setNumList(Arrays.asList(inputStr5));

        game.guess(inputAnswer5);

        List<GuessResult> guessResultList = game.guessHistory();

        assertThat(guessResultList.size()).isEqualTo(1);
        assertThat(guessResultList.get(0).getResult()).isEqualTo("1A0B");
        assertThat(game.checkCoutinue()).isEqualTo(true);

    }

    @Test
    public void guess_result_when_input_six_times_and_fail(){

        Answer inputAnswer6 = new Answer();
        String[] inputStr6 = {"4","3","2","1"};
        inputAnswer6.setNumList(Arrays.asList(inputStr6));

        game.guess(inputAnswer6);
        game.guess(inputAnswer6);
        game.guess(inputAnswer6);
        game.guess(inputAnswer6);
        game.guess(inputAnswer6);
        game.guess(inputAnswer6);

        List<GuessResult> guessResultList = game.guessHistory();

        assertThat(guessResultList.size()).isEqualTo(6);
        assertThat(guessResultList.get(2).getResult()).isEqualTo("0A4B");
        assertThat(game.checkStatus()).isEqualTo("fail");

    }

}
