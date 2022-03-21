package ru.javarush.nikolaev.cryptoanalyzer.commands;

import ru.javarush.nikolaev.cryptoanalyzer.entity.Result;

public interface Action {
    Result run(String[] parameters);
}
