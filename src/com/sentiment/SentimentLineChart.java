package com.sentiment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class SentimentLineChart extends Application {

    private static Map<String, Integer> sentimentData;

    public static void launchChart(Map<String, Integer> data) {
        sentimentData = data;
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sentiment Line Chart");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Sentiment");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Tweet Sentiment Distribution");

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Sentiments");

        for (Map.Entry<String, Integer> entry : sentimentData.entrySet()) {
            dataSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        lineChart.getData().add(dataSeries);

        VBox vbox = new VBox(lineChart);
        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
