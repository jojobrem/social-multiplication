package microservices.book.socialmultiplication.service;

import org.junit.Before;
import org.junit.Test;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomGeneratorServiceImplTest {

    private RandomGeneratorServiceImpl testee;

    @Before
    public void setUp(){
        testee = new RandomGeneratorServiceImpl();
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() {
        List<Integer> randomFactors = IntStream.range(0,1000)
                .map(i -> testee.generateRandomFactor())
                .boxed().collect(Collectors.toList());
        List<Integer> range = IntStream.range(11, 100).boxed().collect(Collectors.toList());
        assertThat(randomFactors).containsOnlyElementsOf(range);
    }
}
