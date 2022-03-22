package ru.javarush.nikolaev.cryptoanalyzer.controllers;

import ru.javarush.nikolaev.cryptoanalyzer.commands.Action;
import ru.javarush.nikolaev.cryptoanalyzer.commands.Bruteforce;
import ru.javarush.nikolaev.cryptoanalyzer.commands.Decoder;
import ru.javarush.nikolaev.cryptoanalyzer.commands.Encoder;
import ru.javarush.nikolaev.cryptoanalyzer.exceptions.AppException;

public enum Activity {
    ENCODE( new Encoder() ),
    DECODE( new Decoder() ),
    BRUTE(new Bruteforce() );


    private final Action action;

    Activity(Action action) {
        this.action = action;
    }

    public static Action get(String actionName) {
        try {
            Activity value = Activity.valueOf( actionName.toUpperCase() );
            return value.action;
        } catch (IllegalArgumentException e) {
            throw new AppException( "not found " + actionName, e );
        }
    }
}