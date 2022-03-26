package ru.javarush.nikolaev.cryptoanalyzer.utils.caesars;

import ru.javarush.nikolaev.cryptoanalyzer.constants.Constants;

import java.io.*;
import java.util.ArrayList;

public class CaesarAlg {
    public void toEncode(String inputFile, String outputFile, int encryptionKeyValue) {

        try (BufferedReader bufferedReader = new BufferedReader( new FileReader( inputFile ) );
             BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( outputFile ) )) {
            ArrayList<String> data = new ArrayList<>();
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                char[] chars = string.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int index = Constants.ALPHABET.indexOf( Character.toLowerCase( chars[i] ) );
                    if (index == -1) {
                        continue;
                    }

                    int shift;
                    if (encryptionKeyValue > 0) {
                        shift = (index - encryptionKeyValue) % Constants.ALPHABET.size();
                    } else shift = (index + encryptionKeyValue) % Constants.ALPHABET.size();
                    if (shift < 0) shift = shift + Constants.ALPHABET.size();
                    chars[i] = Constants.ALPHABET.get( shift );
                }
                data.add( new String( chars ) );
            }

            for (String string : data) {
                bufferedWriter.write( string + "\n" );
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public void toDecode(String inputFile, String outputFile, int encryptionKeyValue) {
        int reversKeyValue = -encryptionKeyValue;
        toEncode( inputFile, outputFile, reversKeyValue );
    }
}
