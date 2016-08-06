package com.wangjingke;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class IO {
    public static void process (String path) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        String subjectID = "";
        CSVWriter writer = new CSVWriter(new FileWriter(path.split(".csv")[0]+"_decoded.csv"));
        CSVReader reader = new CSVReader(new FileReader(path));

        Key madresKey = Enigma.askForKey();

        String[] lineX;
        while ((lineX = reader.readNext()) != null) {
            if (lineX[1].equals("subjectID")) {subjectID = lineX[2];}
            if (lineX[1].equals("LocationTracking")) {
                String[] entryX = {subjectID, lineX[0], Enigma.decode(lineX[2], madresKey), lineX[3]};
                writer.writeNext(entryX);
            }
        }
        writer.close();
    }

}
