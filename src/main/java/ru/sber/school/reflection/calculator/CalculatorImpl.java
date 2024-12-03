package ru.sber.school.reflection.calculator;

import ru.sber.school.reflection.perfomanceproxy.Metric;


public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int arg) {
        if (arg < 0) {
            throw new ArithmeticException("Факториал отрицательного числа не определен.");
        }
        if (arg == 1){
            return 1;
        }
        int count = 1;
        int result = 1;
        while (count <= arg){
            result *= count;
            count++;
        }
        return result;
    }

    private void countCrow() {
        System.out.println("Вызван countCrow");
    }
}
