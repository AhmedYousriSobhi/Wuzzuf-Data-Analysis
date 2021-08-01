package com.WuzzufWebProject.com.WuzzufWebProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smile.data.DataFrame;

@RestController
public class webController {
    @Autowired
    private webServicesDAO webServ;

    /*Function to print CSV file*/
    @GetMapping("/CSV")
        public String csv(@RequestParam(value="name") String fileName) throws Exception{
            return webServ.getFilePath(fileName);

        }
        
    @GetMapping(value = "/count",produces = MediaType.TEXT_HTML_VALUE)
        public String count(@RequestParam(value="service") String col) throws Exception{
            return webServ.printCounts(col);
        }
        
    @GetMapping(value = "/Summary",produces = MediaType.TEXT_HTML_VALUE)
        public String summary() throws Exception{ 
            return webServ.summary();
        } 
        
     @GetMapping(value = "/Structure",produces = MediaType.TEXT_HTML_VALUE)
        public String structure() throws Exception{ 
            return webServ.structure();
        } 
        
    @GetMapping(value = "/Factorized",produces = MediaType.TEXT_HTML_VALUE)
        public String factor() throws Exception{ 
            return webServ.factorized();
        }    
        
     @GetMapping(value = "/BarChart",produces = MediaType.TEXT_HTML_VALUE)
        public String barChart(@RequestParam(value="service") String col) throws Exception{ 
            DisplayHtml html = new DisplayHtml();
            List<String> target = webServ.count(col);     
            List<String> values = new ArrayList<>();
            List<String> counts = new ArrayList<>();
            for (String r : target) {         
                    String[] s = r.split("=");
                    System.out.println(s[0]+"  "+s[1]);
                    values.add(s[0]);
                    counts.add((s[1]));                 
            }
           
          // Create Chart
        PieChart chart =
                new PieChartBuilder().width(800).height(600).title("Pie chart for "+col).build();

        // Customize Chart
        chart.getStyler().setCircular(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);

        for (int i=0; i < values.size() ; i++) {
            chart.addSeries(values.get(i), Integer.parseInt(counts.get(i)));
        }

        String path = new File("").getAbsolutePath()+"\\src\\main\\java\\com\\WuzzufWebProject\\com\\WuzzufWebProject\\";
        BitmapEncoder.saveBitmap(chart,path, BitmapEncoder.BitmapFormat.PNG);
        return html.viewchart(path);          
        }   
        
}
