import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class Main
{

	public static void main(String[] args)
	{
		 String sampleText = "Hello, world!. This is a lovely phone.";
		
		 try
		 {
			 InputStream is = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "models" + System.getProperty("file.separator") + "en-sent.bin");
			 SentenceModel model = new SentenceModel(is);
			 SentenceDetectorME sdetector = new SentenceDetectorME(model);
			
			 String sentences[] = sdetector.sentDetect(sampleText);
			
			 System.out.println(sentences[0]);
			 System.out.println(sentences[1]);
			 is.close();
		 }
		 catch (Exception e)
		 {
			 e.printStackTrace();
		 }
	}
}
