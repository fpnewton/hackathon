import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;


public class Main
{

	public static void main(String[] args)
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples" + System.getProperty("file.separator") + "01.txt"));
			StringBuilder sampleText = new StringBuilder();
			String line;
			
			while((line = in.readLine()) != null)
			{
			    sampleText.append(line);
			}
			
			in.close();
			
			
			InputStream is = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "models" + System.getProperty("file.separator") + "en-sent.bin");
			SentenceModel model = new SentenceModel(is);
			SentenceDetectorME sdetector = new SentenceDetectorME(model);

			String sentences[] = sdetector.sentDetect(sampleText.toString());

			for (int i = 0; i < sentences.length; i++)
			{
				System.out.println("" + i + ": " + sentences[i]);
			}

			is.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
