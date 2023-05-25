package ma.fstt;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.SortieDAO;

import java.sql.SQLException;
import java.util.Map;

public class ChartGeneratorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            ChartGeneratorApp app = new ChartGeneratorApp();
            app.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void run() throws SQLException {
        // Retrieve the overall product counts and most retrieved product per month from the SortieDAO
        SortieDAO sortieDAO = new SortieDAO();
        Map<String, Integer> productCounts = sortieDAO.getOverallProductCounts();
        Map<String, String> mostRetrievedProducts = sortieDAO.getMostRetrievedProductPerMonth();

        // Generate and display the product counts chart
        showProductCountsChart(productCounts);

        // Generate and display the most retrieved product per month chart
        showMostRetrievedProductPerMonthChart(mostRetrievedProducts);
    }

    public void showProductCountsChart(Map<String, Integer> productCounts) {
        // Create the chart axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the bar chart
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Overall Product Counts");

        // Create the data series for the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Product Count");
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            String productReference = entry.getKey();
            int count = entry.getValue();
            series.getData().add(new XYChart.Data<>(productReference, count));
        }

        // Add the data series to the chart
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList(series);
        chart.setData(data);

        // Create the chart scene
        Scene scene = new Scene(chart, 800, 600);

        // Display the chart
        Stage stage = new Stage();
        stage.setTitle("Product Counts Chart");
        stage.setScene(scene);
        stage.show();
    }

    public void showMostRetrievedProductPerMonthChart(Map<String, String> mostRetrievedProducts) {
        // Create the chart axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month - Product");
        NumberAxis yAxis = new NumberAxis();

        // Create the bar chart
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Most Retrieved Product per Month");

        // Create the data series for the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Most Retrieved Product");
        for (Map.Entry<String, String> entry : mostRetrievedProducts.entrySet()) {
            String month = entry.getKey();
            String productReference = entry.getValue();
            series.getData().add(new XYChart.Data<>(month + " - " + productReference, 1));
        }

        // Add the data series to the chart
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList(series);
        chart.setData(data);

        // Create the chart scene
        Scene scene = new Scene(chart, 800, 600);

        // Display the chart
        Stage stage = new Stage();
        stage.setTitle("Most Retrieved Product per Month Chart");
        stage.setScene(scene);
        stage.show();
    }
}
