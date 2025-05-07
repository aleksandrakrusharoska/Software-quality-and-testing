import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorCACC {

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(650, false, 8000, 4, 3.5 + ((double) 8000 / 10000)), //2
                Arguments.of(750, true, 8000, 4, 6.5 + ((double) 8000 / 5000)), //3
                Arguments.of(750, false, 12000, 4, 3.5 + ((double) 12000 / 10000)), //5
                Arguments.of(650, false, 12000, 4, 6.5 + ((double) 12000 / 5000)), //6
                Arguments.of(650, false, 8000, 2, 6.5 + ((double) 8000 / 5000)) //10
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testCalculateDiscountedPrice(int creditScore,
                                             boolean isFirstTimeBorrower,
                                             double loanAmount,
                                             int yearsEmployed,
                                             double expected) {
        double result = Calculator.calculateAdjustedInterestRate(creditScore, isFirstTimeBorrower, loanAmount, yearsEmployed);
        assertEquals(expected, result);
    }

}