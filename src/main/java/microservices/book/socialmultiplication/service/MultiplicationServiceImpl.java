package microservices.book.socialmultiplication.service;

import microservices.book.socialmultiplication.domain.Multiplication;
import microservices.book.socialmultiplication.domain.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;

    @Autowired
    MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService) {
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationResultAttempt attempt) {
        Multiplication multiplication = attempt.getMultiplication();
        int resultAttempt = attempt.getResultAttempt();
        boolean correct = multiplication.getFactorA() * multiplication.getFactorB() == resultAttempt;

        Assert.isTrue(!attempt.isCorrect());

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(), correct);

        return correct;
    }

}
