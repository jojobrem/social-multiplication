package microservices.book.socialmultiplication.domain;

import javax.annotation.sql.DataSourceDefinition;

public class Multiplication {

    private int factorA;
    private int factorB;
    private int result;

    public Multiplication(int factorA, int factorB){
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }
    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getResult() {
        return result;
    }
}
