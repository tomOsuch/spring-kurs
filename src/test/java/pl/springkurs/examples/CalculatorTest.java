package pl.springkurs.examples;

import lombok.extern.java.Log;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static pl.springkurs.examples.Digits.isDigit;

@Log
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @BeforeEach
    void setUp() {
        log.info("Before each");
    }

    @AfterEach
    void tearDown() {
        log.info("After each");
    }

    @BeforeAll
    static void beforeAll() {
        log.info("Before all");
    }

    @AfterAll
    static void afterAll() {
        log.info("After all");
    }

    @DisplayName("given two numbers")
    @Nested
    class GivenTwoNumbers {
        @Test
        void given_two_numbers_when_add_then_returns_their_sum() {
            //given /arrange
            var firstNumber = 1;
            var secondNumber = 3;
            //when / act
            var actual = calculator.add(firstNumber, secondNumber);
            //then / assert
            assertEquals(1 + 3, actual);
        }

        @CsvFileSource(resources = "/data.csv")
        @ParameterizedTest(name = "attempt {index} with values {0} and {1}")
        void when_minus_then_returns_their_difference(double firstNumber, double secondNumber) {
            assertEquals(firstNumber - secondNumber, calculator.minus(firstNumber, secondNumber));
        }
    }

    @Test
    void and_divisor_equals_zero_then_throws_exceptions() {
        assertThrows(IllegalArgumentException.class, () -> calculator.diver(10, 0));
    }

    @Test
    void matchers_test() {
        assertThat(List.of(1, 2, 3, 4, 5), hasItems(2, 4));
        assertThat("Java is fun", both(containsString("va")).and(hasLength(11)));
        assertThat("2.22", isDigit());
    }

}

class Digits extends TypeSafeMatcher<String> {

    public static Digits isDigit() {
        return new Digits();
    }

    @Override
    protected boolean matchesSafely(String value) {
        boolean result;
        try {
            Double.parseDouble(value);
            result = true;
        } catch (NumberFormatException exception) {
            result = false;
        }

        return result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("should contains digits");
    }
}