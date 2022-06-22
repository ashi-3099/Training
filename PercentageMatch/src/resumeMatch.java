
import java.io.IOException;

public class resumeMatch {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		ResumeReader rd = new ResumeReader();
		String[] ar={"leadership","java","problem solving","python"};
		rd.searchKeywords(ar);		
	}

}
