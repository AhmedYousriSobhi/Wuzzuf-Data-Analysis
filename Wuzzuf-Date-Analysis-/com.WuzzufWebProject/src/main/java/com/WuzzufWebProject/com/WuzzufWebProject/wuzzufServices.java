package com.WuzzufWebProject.com.WuzzufWebProject;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import smile.data.DataFrame;

@Component("wuzzufServices")
public class wuzzufServices {
    
    private final String userDirectory = new File("").getAbsolutePath();
    private final String subPath = "\\src\\main\\java\\com\\WuzzufWebProject\\com\\WuzzufWebProject\\";
    private final String CSVFileName = "Wuzzuf_Jobs.csv"; 
    private final String csvFilePath = userDirectory+subPath+CSVFileName;
    
    csvReaderDAO wuzzufCSV = null;
    DataFrame wuzzufData = null;
    
    public wuzzufServices() throws Exception{
        wuzzufCSV = new csvReaderDAO(csvFilePath);
        wuzzufData = wuzzufCSV.readCSVFile();
    } 
    
    public DataFrame getDataFrame(){
        return wuzzufData;
    }
    
    public String getFilePath(){
        return this.csvFilePath;
    }
    
    public void getStatus(){
        wuzzufCSV.printStatus(wuzzufData);
    }
            
    public DataFrame clearData(){
        wuzzufData = wuzzufCSV.clearNullDup(wuzzufData);
        System.out.println("Modified: "+wuzzufData);
        return wuzzufData;
    }       
    
    public DataFrame factorizeFeature(){
        /*Factorize YearsExp feature*/
        String ColumnToFactorize = "YearsExp";
        DataFrame wuzzufDataModified = wuzzufCSV.columnEncoding(wuzzufData,ColumnToFactorize);
        System.out.println(wuzzufDataModified);
        return wuzzufDataModified;
    }
    
    public String[] getHeaders(){
        String[] colNames = wuzzufData.names();
        System.out.println(Arrays.toString(colNames));
        return wuzzufData.names();
    }
    
    public List getMostCommon(String col){                 
        /*most Domand Company*/
        List x = wuzzufCSV.counter(wuzzufData, col,false);
        return x;
    }  
    
    public DataFrame getStructure(){
        return wuzzufCSV.getStructure(wuzzufData);
    }
    
    public DataFrame getSummary(){
        return wuzzufCSV.getSummary(wuzzufData);
    }

}
