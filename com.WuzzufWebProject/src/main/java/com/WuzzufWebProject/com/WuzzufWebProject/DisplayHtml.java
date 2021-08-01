package com.WuzzufWebProject.com.WuzzufWebProject;

import org.apache.commons.codec.binary.Base64;
import org.apache.spark.sql.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import smile.data.DataFrame;

public class DisplayHtml {

    private HTMLTableBuilder builder ;

    public String displayrows(String []head,List<String> ls){
        
        builder=new HTMLTableBuilder(null,true,ls.size(),head.length);
        builder.addTableHeader(head);
        for (String r : ls) {
          System.out.println("row: " + r.toString());
	  String[] s = r.toString().split("=");
          for(String ss: s){
          System.out.println("cell: " + ss);
          }
          builder.addRowValues(s);
    }
        return builder.build();

    }
    
    public String displayrows(String []head,DataFrame target){
      builder=new HTMLTableBuilder(null,true,target.size(),head.length);
      builder.addTableHeader(head);
      for(String[] stringList : target.toStrings(target.size())){
            builder.addRowValues(stringList);       
        }
        return builder.build();
    }
    
    public String viewchart(String path){

        FileInputStream img ;
		try {
			File f= new File(path);
			img = new FileInputStream(f);
                        System.out.println("Before");
			byte[] bytes =  new byte[(int)f.length()];
                        System.out.println("Then");
			img.read(bytes);
                        System.out.println("After");
			String encodedfile = new String(Base64.encodeBase64(bytes) , "UTF-8");

			return "<div>" +
					"<img src=\"data:image/png;base64, "+encodedfile+"\" alt=\"Red dot\" />" +
					"</div>";
		} catch (IOException e) {
			return "error";
		}



    }

}
