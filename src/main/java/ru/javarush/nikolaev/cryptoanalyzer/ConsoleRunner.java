package ru.javarush.nikolaev.cryptoanalyzer;

import ru.javarush.nikolaev.cryptoanalyzer.entity.Result;
import ru.javarush.nikolaev.cryptoanalyzer.exceptions.AppException;

public class ConsoleRunner {
    public static void main(String[] args) throws AppException {
        Application application = new Application();
        try {
            Result result = application.run( args );
        } catch (AppException exception) {
            exception.getMessage();
        } finally {
            System.exit( 1 );
        }
    }
}

