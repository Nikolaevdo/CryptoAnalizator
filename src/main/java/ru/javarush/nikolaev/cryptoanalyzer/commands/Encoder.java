package ru.javarush.nikolaev.cryptoanalyzer.commands;

import ru.javarush.nikolaev.cryptoanalyzer.constants.Constants;
import ru.javarush.nikolaev.cryptoanalyzer.entity.Result;
import ru.javarush.nikolaev.cryptoanalyzer.entity.ResultCode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encoder implements Action {


    public Result run(String[] parameters) {
        String inputFile = parameters[0];
        String outputFile = parameters[1];
        String encryptionKeyString = parameters[2];
        int encryptionKey = Integer.parseInt( encryptionKeyString );
        validateParrams( inputFile, outputFile, encryptionKeyString );
        encoding( inputFile, outputFile, encryptionKey );
        return new Result( "no errors detected,", ResultCode.OK );
    }

    private void encoding(String inputFile, String outputFile, int encryptionKey) {
        try (Scanner fileReader = new Scanner( Files.newBufferedReader( Path.of( inputFile ) ) );
             BufferedWriter fileWriter = Files.newBufferedWriter( Path.of( outputFile ) )) {

            //Gets modulus of a number
            int encKey = Math.abs( encryptionKey % Constants.ALPHABET.size() );
            String line;
            while (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                char[] chars = line.toLowerCase().toCharArray();
                char[] decodedChars = new char[chars.length];
                int alphabetPos = -1;
                int newPos;

                //Decoding new line.
                for (int i = 0; i < chars.length; i++) {
                    char charsArray = chars[i];
                    //finding symbol in ALPHABET.
                    for (int j = 0; j < Constants.ALPHABET.size(); j++) {
                        if (charsArray == Constants.ALPHABET.get( j )) {
                            newPos = j - encKey;
                            if (newPos < 0) {
                                alphabetPos = newPos + Constants.ALPHABET.size();
                            } else {
                                alphabetPos = newPos;
                            }
                            break;
                        }
                    }
                    if (alphabetPos < 0) {
                        decodedChars[i] = chars[i];
                    } else {
                        decodedChars[i] = Constants.ALPHABET.get( alphabetPos );
                        alphabetPos = -1;
                    }
                }
                fileWriter.write( decodedChars );

                if (fileReader.hasNextLine()) {
                    fileWriter.write( "\r\n" );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateParrams(String inputFile, String outputFile, String encryptionKeyString) {
        if (!Files.exists( Path.of( inputFile ) )) {
            System.err.println( "ERROR. Input file must exist." );
        }
        if (!outputFile.endsWith( ".txt" )) {
            System.err.println( "ERROR. Output file name must be *.txt." );
        }
        if (!Files.exists( Path.of( outputFile ) )) {
            try {
                Files.createFile( Path.of( outputFile ) );
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println( "Couldn't create output file." );
            }
        }
        try {
            int encryptionKey = Integer.parseInt( encryptionKeyString );
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }
}