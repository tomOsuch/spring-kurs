package pl.springkurs.examples;

public class Calculator {

    public double add(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }

    public double minus(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }

    public double diver(double firstNumber, double secondNumber) {
        if (secondNumber == 0) {
            throw new IllegalArgumentException();
        }
        return firstNumber / secondNumber;
    }

}
