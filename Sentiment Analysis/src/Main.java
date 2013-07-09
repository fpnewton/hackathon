import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		String sampleText = "This is a lovely phone.";
		
		try {
			InputStream is = new FileInputStream("en-sent.bin");
			SentenceModel model = new SentenceModel(is);
			SentenceDetectorME sdetector = new SentenceDetectorME(model);
	 
			String sentences[] = sdetector.sentDetect(sampleText);
	 
			System.out.println(sentences[0]);
			System.out.println(sentences[1]);
			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
