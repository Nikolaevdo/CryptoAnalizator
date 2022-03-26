package ru.javarush.nikolaev.cryptoanalyzer.controllers;

import ru.javarush.nikolaev.cryptoanalyzer.commands.Action;
import ru.javarush.nikolaev.cryptoanalyzer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters) {
        Action action = Activity.get(actionName);
        return action.run(parameters);
    }
}

