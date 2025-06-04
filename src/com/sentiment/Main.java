package com.sentiment;

import java.util.*;
import java.util.Scanner;
import javafx.application.Platform;

public class Main {
    public static void main(String[] args) {
        String filePath = "resources/twitter_dataset.csv";
        CSVReader reader = new CSVReader(filePath);
        TweetSentimentAnalyzer analyzer = new TweetSentimentAnalyzer();

        List<String> tweets = reader.readTweets();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of tweets to analyze (max " + tweets.size() + "): ");
        int limit = scanner.nextInt();
        scanner.close();

        limit = Math.min(limit, tweets.size());
        List<String> selectedTweets = tweets.subList(0, limit);

        Map<String, Integer> sentimentCount = new HashMap<>();
        int totalScore = 0;

        for (String tweet : selectedTweets) {
            String sentiment = analyzer.getSentiment(tweet);
            int score = sentimentToScore(sentiment);
            totalScore += score;

            sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);

            System.out.println("Tweet: " + tweet);
            System.out.println("Sentiment: " + sentiment);
            System.out.println("-----------------------------");
        }

        System.out.println("=== Overall Sentiment Summary ===");
        for (Map.Entry<String, Integer> entry : sentimentCount.entrySet()) {
            System.out.printf("%-15s : %d (%.2f%%)%n", entry.getKey(), entry.getValue(),
                    (entry.getValue() * 100.0 / limit));
        }

        double averageScore = totalScore / (double) limit;
        System.out.printf("Average Sentiment Score: %.2f%n", averageScore);
        System.out.println("Tweet Count Analyzed   : " + limit);

        new Thread(() -> SentimentDashboard.launchDashboard(sentimentCount)).start();

    }

    private static int sentimentToScore(String sentiment) {
        return switch (sentiment) {
            case "Very Negative" -> 0;
            case "Negative" -> 1;
            case "Neutral" -> 2;
            case "Positive" -> 3;
            case "Very Positive" -> 4;
            default -> 2;
        };
    }
}
