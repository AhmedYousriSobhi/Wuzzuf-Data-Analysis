package com.WuzzufWebProject.com.WuzzufWebProject;

import java.io.File;
import java.util.List;
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
}
