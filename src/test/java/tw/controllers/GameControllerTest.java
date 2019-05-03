package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;
import tw.views.GameView;

import javax.xml.ws.soap.MTOM;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    @Mock
    private AnswerGenerator answerGenerator;
    @Mock
    private Game game;
    @Mock
    private GameView gameView;
    private GameController gameController;
    private Answer answer;

    @Before
    public void setUp() throws Exception{
        //初始化测试用例中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
        answer = Answer.createAnswer("1 2 3 4");
        when(answerGenerator.generate()).thenReturn(answer);

        game = new Game(answerGenerator);
        gameController = new GameController(game,gameView);

    }

    @Test
    public void should_begain_game() throws Exception{

//        AnswerGenerator answerGeneratorMock = mock(AnswerGenerator.class);
//        Answer answer = Answer.createAnswer("1 2 3 4");
//        when(answerGeneratorMock.generate()).thenReturn(answer);
//
//        Game game = new Game(answerGeneratorMock);
//        GameView gameViewMock = mock(GameView.class);
//        GameController gameController = new GameController(game,gameViewMock);

        gameController.beginGame();

        verify(gameView).showBegin();

    }

    @Test
    public void display_the_guessGameResult_when_gameCheckContinue_return_true()throws IOException {

        InputCommand inputCommand = mock(InputCommand.class);
        when(inputCommand.input()).thenReturn(answer);

        Game game = mock(Game.class);
        when(game.checkCoutinue()).thenReturn(true);

        gameController.play(inputCommand);

        //any() return any object
        verify(gameView).showGuessResult(any());
        verify(gameView).showGuessHistory(anyList());
    }

    @Test
    public void display_the_guessGameResult_when_gameCheckContinue_return_false()throws IOException {

        InputCommand inputCommand = mock(InputCommand.class);
        Answer answer = Answer.createAnswer("1 4 5 6");
        when(inputCommand.input()).thenReturn(answer);

        Game game = mock(Game.class);
        when(game.checkCoutinue()).thenReturn(false);

        gameController.play(inputCommand);

        verify(gameView).showGameStatus(anyString());
    }

}