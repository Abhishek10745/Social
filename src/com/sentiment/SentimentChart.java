package com.sentiment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class SentimentChart extends Application {

    public static Map<String, Integer> sentimentData;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sentiment Analysis - Pie Chart");

        PieChart pieChart = new PieChart();

        for (Map.Entry<String, Integer> entry : sentimentData.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        VBox vbox = new VBox(pieChart);
        Scene scene = new Scene(vbox, 600, 400);

        stage.setScene(scene);
        stage.show();
    }

    public static void launchChart(Map<String, Integer> data) {
        sentimentData = data;
        launch();
    }
}
