package microservices.book.socialmultiplication.service;

import microservices.book.socialmultiplication.domain.Multiplication;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl testee;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.testee = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    public void createRandomMultiplicationTest() {
        //given
        given(randomGeneratorService.generateRandomFactor()).willReturn(30, 50);
        //when
        Multiplication multiplication = testee.createRandomMultiplication();
        //then
        assertThat(multiplication.getFactorA()).isEqualTo(30);
        assertThat(multiplication.getFactorB()).isEqualTo(50);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }

    @Test
    public void checkCorrectAttemptTest() {
        //given some changes
        Multiplication multiplication = new Multiplication(50,60);
        int answer = 3000;
        //when
        boolean result = testee.checkAttempt(multiplication, answer);
        //then
        assertThat(result).isTrue();
    }
}
