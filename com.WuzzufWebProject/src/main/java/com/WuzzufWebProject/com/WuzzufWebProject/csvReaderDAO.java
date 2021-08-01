package com.WuzzufWebProject.com.WuzzufWebProject;

import java.awt.Color;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import smile.data.DataFrame;


public class csvReaderDAO { 
    private SmileDataFrame csv_smile ;
   
    /*Constuctor*/        
    public csvReaderDAO(String csvFilePath) {
        this.csv_smile = new SmileDataFrame(csvFilePath);              
    }
    
    public DataFrame readCSVFile() throws Exception{    
        return this.csv_smile.readCSV();   
    }
    
    public void printStatus(DataFrame df){
        System.out.println("Structure");
        csv_smile.printStructure(df);
        System.out.println("Summary");
        csv_smile.printSummary(df);
    }
    
    public DataFrame getSummary(DataFrame df){
        return csv_smile.getSummary(df);
    }
    
    public DataFrame getStructure(DataFrame df){
        return csv_smile.getStructure(df);
    }
            
    public DataFrame clearNullDup(DataFrame df){
        df = csv_smile.removeNullValues(df);
        return df;
    }
    
    public DataFrame columnEncoding(DataFrame df, String col){
        return csv_smile.encodingCatogery(df, col);
    }
    
    public List counter(DataFrame df, String col,Boolean display){
        
        String[] values = df.stringVector(col).toArray();
        System.out.println(Arrays.toString(values));
        Map<String,Integer> repeatationMap = new HashMap<String,Integer>();
        for (String str : values){
            if(repeatationMap.containsKey(str)){
                repeatationMap.put(str,repeatationMap.get(str)+1);
            }
            else
                repeatationMap.put(str, 1);
        }
        
        /*Sorting Hushmap in order*/
        List<Map.Entry<String,Integer>> list = new LinkedList<Map.Entry<String,Integer>>(repeatationMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer> >(){
           @Override
           public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
               return(o2.getValue()).compareTo(o1.getValue()); //Decending
           } 
        });
        System.out.println(list.toString());
        /*put data for sortedList to hashmap*/
        String maxValue = list.get(0).toString();
        /*Priniting highest One*/
        System.out.println("Most Dommand : "+maxValue);
        List<String> stringList = new LinkedList<String>();
        for(Map.Entry<String,Integer> entry : list){
            StringBuilder row = new StringBuilder();
            row.append(entry.getKey());
            row.append("=");
            row.append(entry.getValue());
            stringList.add(row.toString());
        }
        return stringList;//temp;
    }
      
}
