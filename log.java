package log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class log {

	private static final String e = "ERROR";
	private static final String f = "FATAL";
	private static final String c = "CRITICAL";
	private static final String a = "com.sinovatech.myservice.service.PointFileProcessor ";
	private static final String b = "send_data_to_cas.py";
	private static final DateFormat Java_Date_Format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	private static final DateFormat Python_Date_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	private static final DateFormat Output_Date_Format = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
	
	public static void main(String[] args) {
		
		FileInputStream inStream;
		FileOutputStream outStream;
		try {

			
			//Input stream
			inStream = new FileInputStream("D:/temp/PointCalculator_20200211.log");
			outStream = new FileOutputStream("D:/temp/output.txt");
			
			//Stream Reader
			InputStreamReader isr = new InputStreamReader(inStream);
			OutputStreamWriter osw = new OutputStreamWriter(outStream);
			
			//buffer reader
			BufferedReader reader = new BufferedReader(isr);
			
            List<String> strList = new ArrayList<>();
                 String line = reader.readLine();
            
            while (line!=null) {
            	strList.add(line);
            	line = reader.readLine();
            }
            inStream.close();
            
            inStream = new FileInputStream("D:/temp/Python.log");
            
            InputStreamReader isr1 = new InputStreamReader(inStream);
            BufferedReader reader1 = new BufferedReader(isr1);
            String line1 = reader1.readLine();
            while (line1!=null) {
            	strList.add(line1);
            	line1 = reader1.readLine();
            }
            inStream.close();
            
            List<String> List = new ArrayList<>();
            for (String str1 : strList) {
            	if (str1.contains(f) == true || str1.contains(e) == true || str1.contains(c) == true) {
            		if(str1.contains(c) == true) {
            			str1 = str1.replaceAll(c, f);
            		}
            		List.add(str1);
            		
            	}
            }
           List<String> List1 = new ArrayList<>();
           for(String str2: List) {
        	   if(str2.contains(e) == true && str2.contains(a) == true) {
        		  String level = str2.substring(1,str2.indexOf("]")).trim();
        		  
        		  String time =str2.substring(str2.indexOf("]") + 1, str2.lastIndexOf("[")).trim();
        		  
        		  Date strDate = Java_Date_Format.parse(time);
        		  String date = Output_Date_Format.format(strDate);
        		  String ny = str2.substring(str2.indexOf("com"));
        		  
        		  StringBuilder sb = new StringBuilder();
        		  sb.append(level);
        		  sb.append("-");
        		  sb.append(date);
        		  sb.append("-");
        		  sb.append(ny);
        		  
        		  str2 = sb.toString();
        		  List1.add(str2);
        		  
        		 }
        	   if(str2.contains(f) == true && str2.contains(a) == true) {
         		  String level = str2.substring(1,str2.indexOf("]")).trim();
         	   
         		  String time =str2.substring(str2.indexOf("]") + 1, str2.lastIndexOf("[")).trim();
         		  
         		  Date strDate = Java_Date_Format.parse(time);
         		  String date = Output_Date_Format.format(strDate);
         		  String ny = str2.substring(str2.indexOf("com"));
         		  
         		  StringBuilder sb = new StringBuilder();
         		  sb.append(level);
         		  sb.append("-");
         		  sb.append(date);
         		  sb.append("-");
         		  sb.append(ny);
         		  
         		  str2 = sb.toString();
         		  List1.add(str2);
           } 
        	   if(str2.contains(e) == true && str2.contains(b) == true) {
        		   
        		   String level = str2.substring(str2.indexOf("E"),str2.indexOf("C")-1).trim();
//             		System.out.println(level);
        		   
        		   
        		   
        		   String time =str2.substring(0, str2.indexOf("s"));
        		   Date strDate = Python_Date_Format.parse(time);
           		  String date = Output_Date_Format.format(strDate);
//                  System.out.println(date);
           		
           		  
           		  
           		  String ny = str2.substring(str2.indexOf(b));
           		  String ny1 = ny.replaceAll(": ERROR","-").trim();
//           		  System.out.println(ny);
//           		  System.out.println(ny1);
          		  
          		  
          		  
          		  
          		  
          		  StringBuilder sb = new StringBuilder();
          		  sb.append(level);
          		  sb.append("-");
          		  sb.append(date);
          		  sb.append("-");
          		  sb.append(ny1);
          		  
          		  str2 = sb.toString();
          		  List1.add(str2);
//          		System.out.println(str2);
           } 
        	   if(str2.contains(f) == true && str2.contains(b) == true) {
          		  String level = str2.substring(str2.indexOf("F"),str2.indexOf("L")+1).trim();
//          		      System.out.println(level);
          		      
          		      
          		      
             		  String time =str2.substring(0, str2.indexOf("s"));
             		  Date strDate = Python_Date_Format.parse(time);
             		  String date = Output_Date_Format.format(strDate);
//             		  System.out.println(date);
             		  
             		  
             		  
             		  
             		  String ny = str2.substring(str2.indexOf(b));
             		  String ny1 = ny.replaceAll(": FATAL","-").trim();
//             		  System.out.println(ny1);
             		 
          		  
          		  StringBuilder sb = new StringBuilder();
          		  sb.append(level);
          		  sb.append("-");
          		  sb.append(date);
          		  sb.append("-");
          		  sb.append(ny1);
          		  
          		  
          		  str2 = sb.toString();
          		  List1.add(str2);
//          		System.out.println(str2);
//          		System.out.println(List1);
        	   }
        	   }  
           for(int i = 1; i < List1.size(); i++) {
        	   for(int j = 0; j<List1.size()-1;j++) {
        		   String a = List1.get(j).substring(6, 23);
//        		   System.out.println(a);
        		   String b = List1.get(j+1).substring(6, 23);
//        		   System.out.println(b);
        		   if(a.compareTo(b)>0) {
        			   String aa = List1.get(j);
        			   String bb = List1.get(j+1);
        			   List1.set(j, bb);
        			   List1.set(j+1,aa);
        		   }
        	   }
           }
           for (int i = 0;i<List1.size();i++) {
        	   String value = List1.get(i);
        	   osw.write(value);
        	   osw.write("\n");
           }
           osw.flush();
           osw.close();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
	} 	
			
			