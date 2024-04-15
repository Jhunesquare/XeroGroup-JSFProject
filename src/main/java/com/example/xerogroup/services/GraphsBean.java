package com.example.xerogroup.services;

import com.example.xerogroup.utils.ConnectionDatabase;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.axes.radial.RadialScales;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearAngleLines;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearPointLabels;
import org.primefaces.model.charts.axes.radial.linear.RadialLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.bubble.BubbleChartDataSet;
import org.primefaces.model.charts.bubble.BubbleChartModel;
import org.primefaces.model.charts.data.BubblePoint;
import org.primefaces.model.charts.data.NumericPoint;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.donut.DonutChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;
import org.primefaces.model.charts.scatter.ScatterChartModel;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Named("graphsBean")
@SessionScoped
public class GraphsBean implements Serializable {
    private BarChartModel barModel;

    private PieChartModel pieModel;

    private BarChartModel barModel2;

    @PostConstruct
    public void init() {

        createBarModel();
        createPieModel();
        //createBarModel2();

    }

    //void//
    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        PieChartOptions optionsPie = new PieChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cantidad de personas que compran un plan turistico");
        optionsPie.setTitle(title);
        pieModel.setOptions(optionsPie);

        List<Number> values = new ArrayList<>();

        List<Integer> totalComprasPorMes = totalComprasPorMes();

        for (Integer compras : totalComprasPorMes) {
            values.add(compras);
        }
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(66, 255, 81)");
        bgColors.add("rgb(255, 153, 0)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Zoológico de Cali");
        labels.add("Panaca");
        labels.add("Santa Marta");
        labels.add("Salitre Magico");
        labels.add("Zoológico de Pereira");
        data.setLabels(labels);

        pieModel.setData(data);
    }

    public void createBarModel() {

        barModel = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Compras por Mes");

        List<Number> values = new ArrayList<>();
        List<Integer> totalComprasPorMes = new ArrayList<>();

        for (int i = 6; i <= 12; i++) {
            totalComprasPorMes = puntosPorMes(i);
            values.add(totalComprasPorMes.isEmpty() ? 0 : totalComprasPorMes.get(0)); // Si está vacío, agrega 0, de lo contrario, agrega el primer elemento.
        }

        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Junio");
        labels.add("Julio");
        labels.add("Agosto");
        labels.add("Septiembre");
        labels.add("Octubre");
        labels.add("Noviembre");
        labels.add("Diciembre");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        //aca iba options
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Cantidad de planes comprados por mes");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    public List<Integer> puntosPorMes(int month) { //de hecho es el total de compras
        List<Integer> totalComprasPorMes = new ArrayList<>();   // por mes pero me da pereza cambiarlo


        String query = "SELECT COUNT(*) AS total_compras\n" +
                "FROM compra c\n" +
                "INNER JOIN detaCompra dc ON c.id = dc.id_comp\n" +
                "INNER JOIN planTuristico pt ON dc.id_plan = pt.id\n" +
                "WHERE DATE_PART('year', c.fec_creacion) = 2023\n" +
                "AND DATE_PART('month', c.fec_creacion) = ?\n" +
                "GROUP BY DATE_PART('month', c.fec_creacion)\n" +
                "ORDER BY total_compras DESC";

        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, month);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int totalCompras = rs.getInt("total_compras");
                    totalComprasPorMes.add(totalCompras);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalComprasPorMes;
    }

    public List<Integer> totalComprasPorMes() {
        List<Integer> totalComprasPorMes = new ArrayList<>();

        String query = "SELECT pt.titulo AS nombre_plan_turistico, COUNT(*) AS total_compras " +
                "FROM compra c " +
                "INNER JOIN detaCompra dc ON c.id = dc.id_comp " +
                "INNER JOIN planTuristico pt ON dc.id_plan = pt.id " +
                "GROUP BY pt.titulo " +
                "ORDER BY total_compras DESC";

        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int totalCompras = rs.getInt("total_compras");
                totalComprasPorMes.add(totalCompras);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalComprasPorMes;
    }




    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
}
