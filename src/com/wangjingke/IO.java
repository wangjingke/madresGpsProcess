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
import java.util.ArrayList;
import java.util.List;

public class IO {
    public static void process (String path) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        String subjectID = "";
        CSVWriter writer = new CSVWriter(new FileWriter(path.split(".csv")[0]+"_decoded.csv"));
        CSVReader reader = new CSVReader(new FileReader(path));

        Key madresKey = Enigma.askForKey();

        String[] lineX;
        while ((lineX = reader.readNext()) != null) {
            if (lineX[1].equals("SubjectID")) {subjectID = lineX[2];}
            if (lineX[1].equals("Tracking")) {
                List<String> entryX = new ArrayList<String>();
                entryX.add(subjectID);
                entryX.add(lineX[0]);
                entryX.add(Enigma.decode(lineX[2], madresKey));
                for (int i = 3; i < lineX.length; i++) {
                    entryX.add(lineX[i]);
                }
                writer.writeNext(entryX.toArray(new String[0]));
            }
        }
        writer.close();
    }
}
