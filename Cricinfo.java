import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Cricinfo extends TimerTask {
    public static void demo() throws Exception {
        URL oracle = new URL("http://www.espncricinfo.com/rss/content/story/feeds/6.xml");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));
        StringBuffer sbf=new StringBuffer();

        String inputLine;
        int i=0;
       
        while ((inputLine = in.readLine()) != null){
        	if(i!=1)
        	{
            	//System.out.println(inputLine);
        		sbf.append(inputLine);
        		
        	}	
        
        //	System.out.println(inputLine);
        	i++;
            }
        in.close();
        
        
      // File inputFile = new File(sbf.toString());
        DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("http://www.espncricinfo.com/rss/content/story/feeds/6.xml");
        doc.getDocumentElement().normalize();
     System.out.println("Root element :"+ doc.getDocumentElement().getNodeName());

 //************************************parsing first tag**************************      
        NodeList list=doc.getElementsByTagName("channel");

        Node temp=list.item(0);                
        Element e=(Element) temp;
        
        
        NodeList list1=e.getElementsByTagName("title");
        Node temp1=list1.item(0);                
        Element e1=(Element) temp1;        
        System.out.println(e1.getTextContent());
        
        
        NodeList list2=e.getElementsByTagName("item");
        for(int j=0;j<list2.getLength();j++)
        {	
        	Node temp2=list2.item(j);                
        	Element e2=(Element) temp2;        
        	
        	 NodeList list3=e2.getElementsByTagName("title");
        	 Node temp3=list3.item(0);                
         	 Element e3=(Element) temp3; 
         		System.out.println("Headlines today:-");
         		System.out.println(e3.getTextContent());
         		System.out.println("  ");
         	
         	NodeList list4=e2.getElementsByTagName("description");
            Node temp4=list4.item(0);                
            Element e4=(Element) temp4; 
            
            System.out.println("Description:-");
            System.out.println(e4.getTextContent());
            
            
            NodeList list5=e2.getElementsByTagName("pubDate");
            Node temp5=list5.item(0);                
            Element e5=(Element) temp5; 
            System.out.println("pubDate:-");
            System.out.println(e5.getTextContent());
            System.out.println("**********************************");
            
        }  
        
        
    
        
        
        
      
    }
    
    public static void main(String args[]) throws Exception
    {
    
    	Timer timer = new Timer();
		timer.schedule(new Cricinfo(), 0, 5000);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			demo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("entered");
	
		
	}
}