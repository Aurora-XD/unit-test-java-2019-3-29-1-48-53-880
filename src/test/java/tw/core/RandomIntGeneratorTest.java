package tw.core;


import org.junit.Rule;
import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Test
    public void should_get_valid_Randomint(){

        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        String randomNumStr = randomIntGenerator.generateNums(10,4);
        List<String> numStrsList = Arrays.asList(randomNumStr.split(" "));

        assertThat(numStrsList.size()).isEqualTo(4);

        for (String numStr : numStrsList) {
            assertThat(Integer.parseInt(numStr)<10).isEqualTo(true);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_thrown_illegalArgumentException_when_digitmax_less_than_numbersOfNeed(){

        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        String randomNumStr = randomIntGenerator.generateNums(2,4);

    }

}