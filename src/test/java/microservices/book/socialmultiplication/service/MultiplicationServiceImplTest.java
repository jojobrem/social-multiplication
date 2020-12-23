package microservices.book.socialmultiplication.service;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import microservices.book.socialmultiplication.domain.User;
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
        //given
        Multiplication multiplication = new Multiplication(50, 60);
        int resultAttempt = 3000;
        //when
        boolean result = testee.checkAttempt(new MultiplicationResultAttempt(new User("joe"), multiplication, resultAttempt));
        //then
        assertThat(result).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {
        //given
        Multiplication multiplication = new Multiplication(50,60);
        int resultAttempt = 3001;
        //when
        boolean result = testee.checkAttempt(new MultiplicationResultAttempt(new User("joe"), multiplication, resultAttempt));
        //then
        assertThat(result).isFalse();
    }
}
