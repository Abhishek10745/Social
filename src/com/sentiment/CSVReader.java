package com.sentiment;

import java.io.*;
import java.util.*;

public class CSVReader {
    private final String filePath;

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readTweets() {
        List<String> tweets = new ArrayList<>();
        try (com.opencsv.CSVReader reader = new com.opencsv.CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                if (line.length > 2) {
                    tweets.add(line[2]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tweets;
    }
}
