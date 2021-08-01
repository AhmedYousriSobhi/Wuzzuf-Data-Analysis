package com.WuzzufWebProject.com.WuzzufWebProject;

import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.io.Read;


public class SmileDataFrame {
    
    private String csvFilePath = "";

    public SmileDataFrame(String csvFilePath){
        setcsvFilePath(csvFilePath);
    } 
    
    public void setcsvFilePath(String csvFilePath){
        this.csvFilePath = csvFilePath;
    }
    
    /*Description: To get DataFrame of the CSV FIle*/
    public DataFrame readCSV() throws Exception{
        CSVFormat format= CSVFormat.DEFAULT.withFirstRecordAsHeader() ;
        DataFrame df = null;
        df = Read.csv(this.csvFilePath,format); 
        return df;
    }
    
    /*Description: to print Structure of DataFrame*/
    public void printStructure(DataFrame df){
        System.out.println(df.structure());
    }
    
    /*Description: to get Structure as DataFrame*/
    public DataFrame getStructure(DataFrame df){
        return df.structure();
    }
    
    /*Description: to print summary of DataFrame*/
    public void printSummary(DataFrame df){
        System.out.println(df.summary());
    }
    
    /*Description: to get Summary as DataFrame*/
    public DataFrame getSummary(DataFrame df){
        return df.summary();
    }
    
    /*Description: to remove null values from DataFrame*/
    public static DataFrame removeNullValues(DataFrame df){
        return df.omitNullRows();
    }
    
    public int[] factorizeCol(DataFrame df, String col){
        String[] values = df.stringVector(col).distinct().toArray(new String[]{});
        int[] intValues = df.stringVector(col).factorize(new NominalScale(values)).toIntArray();
        return intValues;
    }
    
    public DataFrame encodingCatogery(DataFrame df, String col){
        DataFrame dfTemp = df;
         return dfTemp.merge(IntVector.of(col+"to Encoded",factorizeCol(dfTemp,col)));
    }
    
}
