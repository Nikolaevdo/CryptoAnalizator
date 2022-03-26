package ru.javarush.nikolaev.cryptoanalyzer.commands;

import ru.javarush.nikolaev.cryptoanalyzer.entity.Result;
import ru.javarush.nikolaev.cryptoanalyzer.entity.ResultCode;
import ru.javarush.nikolaev.cryptoanalyzer.utils.caesars.CaesarAlg;

public class Decoder implements Action {
    @Override
    public Result run(String[] parameters) {
        String inputFile = parameters[0];
        String outputFile = parameters[1];
        int encryptionKeyValue = Integer.parseInt(parameters[2]);

        CaesarAlg caesarAlg = new CaesarAlg();
        caesarAlg.toDecode( inputFile, outputFile, encryptionKeyValue );

        return new Result( "File is Encode", ResultCode.OK );
    }

}
