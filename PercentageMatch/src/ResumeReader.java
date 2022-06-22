
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper; 


public class ResumeReader {

	public void searchKeywords(String[] str) throws IOException
	{		
		
		 String path = "C:\\Users\\ASHAGRAW\\OneDrive - Capgemini\\Desktop\\demoresumes\\";
	     
		 File file = new File(path);      
		 File[] listOfFiles = file.listFiles();
		 
	      for (File eachfile : listOfFiles) {
	         
	    	  if (eachfile.isFile()) 
	    	  {	   
	    		 PDDocument document = PDDocument.load(eachfile);
	                  PDFTextStripper pdfStripper = new PDFTextStripper();
		            String[] lines= pdfStripper.getText(document).split("\r\n|\r|\n");
		            int count=0;
		            for(String temp:lines)
		            {
		            	for(int i=0;i<str.length;i++)
		            	{
		            		if(temp.toLowerCase().contains(str[i]))
		            		{
		    	                count++;
		            		}
		            		continue;
		            	}
		            }
		            //System.out.println(count);
		            //Finding %match of resume to requirement
		            int match=100*count/(str.length);
		            System.out.println(match+"% matched");
		            if(match<=50) {
		            	System.out.println(eachfile.getName()+" Not selected: "+match+"% matched to our requirement.");	
		            }
		            else
		            {
		            	System.out.println(eachfile.getName()+" Selected: "+match+"% matched to our requirement.");
		            }
	           
		            document.close();                     
	          }
	      } 
	}
	
}