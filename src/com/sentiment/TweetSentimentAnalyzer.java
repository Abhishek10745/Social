package com.sentiment;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import java.util.*;

public class TweetSentimentAnalyzer {
    private final StanfordCoreNLP pipeline;

    // Expanded negative keywords
    private final List<String> negativeKeywords = Arrays.asList(
            "hate", "worst", "bad", "angry", "stupid", "fail", "sucks", "terrible", "horrible",
            "awful", "disgusting", "lame", "useless", "pathetic", "annoying", "ridiculous", "trash",
            "boring", "dumb", "disappointed", "crap", "unbearable", "regret", "waste", "pain", "sad",
            "rude", "depressing", "mediocre", "nonsense", "frustrating", "gross", "nasty", "offensive"
    );

    // Expanded positive keywords
    private final List<String> positiveKeywords = Arrays.asList(
            "love", "amazing", "awesome", "excellent", "fantastic", "great", "wonderful", "brilliant",
            "superb", "cool", "nice", "best", "happy", "delightful", "spectacular", "perfect",
            "outstanding", "impressive", "top", "beautiful", "grateful", "pleased", "fun", "joyful",
            "smooth", "incredible", "charming", "helpful", "enthusiastic", "motivated", "celebrate",
            "win", "success", "refreshing"
    );

    // Phrase boosts with score adjustments (+ positive, - negative)
    private final Map<String, Float> phraseBoosts = Map.ofEntries(
            Map.entry("absolutely love", 2.5f),
            Map.entry("highly recommend", 2.0f),
            Map.entry("never again", -2.5f),
            Map.entry("total disaster", -2.5f),
            Map.entry("not happy", -2.0f),
            Map.entry("very disappointed", -2.0f),
            Map.entry("extremely satisfied", 2.5f),
            Map.entry("so happy", 2.0f),
            Map.entry("really bad", -2.0f),
            Map.entry("love it", 2.0f)
    );

    public TweetSentimentAnalyzer() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
        pipeline = new StanfordCoreNLP(props);
    }

    public String getSentiment(String text) {
        if (text == null || text.trim().isEmpty()) return "Neutral";

        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);

        int maxCoreNLPScore = Integer.MIN_VALUE;

        System.out.println("🔍 Analyzing Tweet: " + text);
        System.out.println("--------------------------------------------------");

        for (CoreSentence sentence : doc.sentences()) {
            String sentiment = sentence.sentiment();
            int rawScore = sentimentToScore(sentiment); // 0-4

            System.out.println("Sentence: " + sentence.text());
            System.out.println("Sentiment: " + sentiment + " (Raw Score: " + rawScore + ")");
            System.out.println("--------------------------------------------------");

            if (rawScore > maxCoreNLPScore) {
                maxCoreNLPScore = rawScore;
            }
        }

        if (maxCoreNLPScore == Integer.MIN_VALUE) {
            maxCoreNLPScore = 2; // default Neutral if no sentences
        }

        // Normalize CoreNLP max score to 0–10 scale
        float normalizedScore = maxCoreNLPScore * 2.5f;

        // Apply lexical keyword boosts (stronger bias)
        if (hasStrongNegativeKeywords(text)) {
            normalizedScore = Math.max(normalizedScore - 2.5f, 0f);
            System.out.println("Lexical negative keywords found: lowering score by 2.5");
        }
        if (hasStrongPositiveKeywords(text)) {
            normalizedScore = Math.min(normalizedScore + 2.5f, 10f);
            System.out.println("Lexical positive keywords found: raising score by 2.5");
        }

        // Apply phrase-level boosts
        for (Map.Entry<String, Float> entry : phraseBoosts.entrySet()) {
            if (text.toLowerCase().contains(entry.getKey())) {
                normalizedScore += entry.getValue();
                System.out.printf("Phrase '%s' detected: adjusting score by %.2f%n", entry.getKey(), entry.getValue());
            }
        }

        // Clamp between 0 and 10 after all boosts
        normalizedScore = Math.max(0f, Math.min(normalizedScore, 10f));

        System.out.printf("Final normalized score: %.2f%n", normalizedScore);

        return mapScoreToSentiment(normalizedScore);
    }

    private boolean hasStrongNegativeKeywords(String text) {
        String lower = text.toLowerCase();
        return negativeKeywords.stream().anyMatch(lower::contains);
    }

    private boolean hasStrongPositiveKeywords(String text) {
        String lower = text.toLowerCase();
        return positiveKeywords.stream().anyMatch(lower::contains);
    }

    private int sentimentToScore(String sentiment) {
        return switch (sentiment) {
            case "Very Negative" -> 0;
            case "Negative" -> 1;
            case "Neutral" -> 2;
            case "Positive" -> 3;
            case "Very Positive" -> 4;
            default -> 2;
        };
    }

    private String mapScoreToSentiment(float score) {
        if (score <= 1.5f) return "Very Negative";
        else if (score <= 3.5f) return "Negative";
        else if (score <= 6.5f) return "Neutral";
        else if (score <= 8.5f) return "Positive";
        else return "Very Positive";
    }
}
