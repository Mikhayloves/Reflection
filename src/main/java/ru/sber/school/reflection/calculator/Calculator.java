package ru.sber.school.reflection.calculator;

import ru.sber.school.reflection.cacheproxy.Cache;
import ru.sber.school.reflection.perfomanceproxy.Metric;

public interface Calculator {
    @Metric
    @Cache
    int calc(int arg);
}
