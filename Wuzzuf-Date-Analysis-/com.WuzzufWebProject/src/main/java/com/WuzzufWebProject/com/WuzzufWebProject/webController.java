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
        
    /*Function to count specific column in decending Order*/   
    @GetMapping(value = "/count",produces = MediaType.TEXT_HTML_VALUE)
        public String count(@RequestParam(value="service") String col) throws Exception{
            return webServ.printCounts(col);
        }
     
    /*Function to display some of the Data in CSV File*/   
    @GetMapping(value = "/display",produces = MediaType.TEXT_HTML_VALUE)
        public String display() throws Exception{ 
            return webServ.display();
        }
        
    /*Function to get Summary*/   
    @GetMapping(value = "/Summary",produces = MediaType.TEXT_HTML_VALUE)
        public String summary() throws Exception{ 
            return webServ.summary();
        }
        
    /*Function to get Structure*/    
    @GetMapping(value = "/Structure",produces = MediaType.TEXT_HTML_VALUE)
        public String structure() throws Exception{ 
            return webServ.structure();
        } 
        
    /*Function to get Factorized*/    
    @GetMapping(value = "/Factorized",produces = MediaType.TEXT_HTML_VALUE)
        public String factor() throws Exception{ 
            return webServ.factorized();
        }    
        
     @GetMapping(value = "/BarChart",produces = MediaType.TEXT_HTML_VALUE)
        public String barChart(@RequestParam(value="service") String col) throws Exception{ 
            return webServ.barChart(col);
        }
        
        @GetMapping(value = "/PieChart",produces = MediaType.TEXT_HTML_VALUE)
        public String pieChart(@RequestParam(value="service") String col) throws Exception{ 
               return webServ.pieChart(col);
        }    
}
