package com.WuzzufWebProject.com.WuzzufWebProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smile.data.DataFrame;

@Component("webServicesDAO")
public class webServicesDAO{
    @Autowired
    private wuzzufServices service;
    
    public webServicesDAO()throws Exception{
        service = new wuzzufServices();
    }
    public String getFilePath(String fileName){
        return service.getFilePath();
    }  
    
    public String display(){
        DisplayHtml html = new DisplayHtml();
        return html.displayrows(service.getDataFrame().names(),service.getDataFrame());
    }
    
    public String summary(){
        DisplayHtml html = new DisplayHtml();
        DataFrame target = service.getSummary();           
        String[] headers = {"column", "count", "min", "avg", "max"};
        return html.displayrows(headers,target);
    }
    
    public String structure(){
        DisplayHtml html = new DisplayHtml();
        DataFrame target = service.getSummary();           
        String[] headers = {"Company", "Type", "Measure"};
        return html.displayrows(headers,target);
    }
    
     public String factorized(){
        DisplayHtml html = new DisplayHtml();
        DataFrame target = service.factorizeFeature();           
        String[] headers = target.names();
        return html.displayrows(headers,target);
    }
    
    public List count(String col){
        return service.getMostCommon(col);
        
    }
    public String printCounts(String col){
        String[] hash = {col,"Count"};
        DisplayHtml html = new DisplayHtml();
        List target = service.getMostCommon(col);
        return html.displayrows(hash,target);
    }
    
    public String barChart(String col) throws Exception{
        DisplayHtml html = new DisplayHtml();
        List<String> target = service.getMostCommon(col);     
        List<String> values = new ArrayList<>();
        List<Long> counts = new ArrayList<>();
        for (String r : target) {
            String[] s = r.split("=");
            System.out.println(s[0]+"  "+s[1]);
            values.add(s[0]);
            counts.add(Long.parseLong(s[1]));                 
            }
        CategoryBarChart chart = new CategoryBarChart();
        CategoryChart x = chart.barChart(col, col, values.subList(0,100), counts.subList(0,100));
        String path = new File("").getAbsolutePath()+"\\src\\main\\java\\com\\WuzzufWebProject\\com\\WuzzufWebProject\\";
        BitmapEncoder.saveBitmap(x,path, BitmapEncoder.BitmapFormat.PNG);
        return html.viewchart(path+".png");
    }
    
    public String pieChart(String col) throws Exception{
        DisplayHtml html = new DisplayHtml();
        List<String> target = service.getMostCommon(col);     
        List<String> values = new ArrayList<>();
        List<String> counts = new ArrayList<>();
        for (String r : target) {         
            String[] s = r.split("=");
            System.out.println(s[0]+"  "+s[1]);
            values.add(s[0]);
            counts.add((s[1]));                 
        }    
        PieChart chart =
                new PieChartBuilder().width(1080).height(720).title("Pie chart for "+col).build();

        // Customize Chart
        chart.getStyler().setCircular(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);

        for (int i=0; i < values.size() ; i++) {
            chart.addSeries(values.get(i), Integer.parseInt(counts.get(i)));
        }
        String path = new File("").getAbsolutePath()+"\\src\\main\\java\\com\\WuzzufWebProject\\com\\WuzzufWebProject\\";
        BitmapEncoder.saveBitmap(chart,path, BitmapEncoder.BitmapFormat.PNG);
        return html.viewchart(path+".png");
    }
}
