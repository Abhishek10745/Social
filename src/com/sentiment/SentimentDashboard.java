package com.sentiment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;

import java.util.Map;

public class SentimentDashboard extends Application {

    private static Map<String, Integer> sentimentData;

    public static void launchDashboard(Map<String, Integer> data) {
        sentimentData = data;
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sentiment Analysis Dashboard");

        // Pie Chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : sentimentData.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Tweet Sentiment Distribution (Pie Chart)");

        // Line Chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Tweet Sentiment Over Categories");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Sentiment Count");

        for (Map.Entry<String, Integer> entry : sentimentData.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        lineChart.getData().add(series);

        // Label
        Label heading = new Label("Sentiment Analysis Results");
        heading.setFont(new Font("Arial", 20));
        heading.setTextFill(Color.DARKBLUE);

        VBox vbox = new VBox(15, heading, pieChart, lineChart);
        Scene scene = new Scene(vbox, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
