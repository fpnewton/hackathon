import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import sentiment.analysis.NegativeWords;
import sentiment.analysis.PositiveWords;



public class Main
{
	static SampleLoader	loader	= new SampleLoader(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples");
	static NLP				nlp		= new NLP();
	
	
	public static void main(String[] args)
	{
		OutputWriter.openStream();
		
		try
		{
			//04, Short Text
			String fileName = "Short text.txt";
			BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples" + System.getProperty("file.separator") + fileName));
			String line;
			
			int positive = 0;
			int negative = 0;
			
			while (true)
			{
				/*ParserThread threads[1] = null;
				ParserThread threads[2] = null;
				ParserThread threads[3] = null;
				ParserThread threads[4] = null;
				ParserThread threads[5] = null;
				ParserThread threads[6] = null;
				ParserThread threads[7] = null;
				ParserThread threads[8] = null;*/
				
				ParserThread[] threads = new ParserThread[8];
				
				if ((line = in.readLine()) != null)
				{
					threads[0] = new ParserThread(line);
				}
				if ((line = in.readLine()) != null)
				{
					threads[1] = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					threads[2] = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					threads[3] = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					threads[4] = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					threads[5] = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					threads[6] = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					threads[7] = new ParserThread(line);
				}
				
				
				
				while ((threads[0] != null && threads[0].t.isAlive()) || (threads[1] != null && threads[1].t.isAlive()) || (threads[2] != null && threads[2].t.isAlive()) || (threads[3] != null && threads[3].t.isAlive()) || (threads[4] != null && threads[4].t.isAlive()) || (threads[5] != null && threads[5].t.isAlive()) || (threads[6] != null && threads[6].t.isAlive()) || (threads[7] != null && threads[7].t.isAlive())) {}
				
				for (ParserThread pt : threads) {
					if (pt != null) {
						positive += pt.positive;
						negative += pt.negative;
					}
				}
				//positive += threads[1].positive + threads[2].positive + threads[3].positive + threads[4].positive + threads[5].positive + threads[6].positive + threads[7].positive + threads[8].positive;
				//negative += threads[1].negative + threads[2].negative + threads[3].negative + threads[4].negative + threads[5].negative + threads[6].negative + threads[7].negative + threads[8].negative;
				
				if (threads[0] == null && threads[1] == null && threads[2] == null && threads[3] == null && threads[4] == null && threads[5] == null && threads[6] == null && threads[7] == null)
				{
					break;
				}
				
				//parse(line);
			}
			
			in.close();
			
			OutputWriter.writeToFile("-------------------------------------------");
			OutputWriter.writeToFile("-------------------------------------------");
			OutputWriter.writeToFile("-------------------------------------------");
			OutputWriter.writeToFile("Overall Positive: " + positive);
			OutputWriter.writeToFile("Overall Negative: " + negative);
			int result = (positive - negative);
			OutputWriter.writeToFile("Overall sentiment is "+(result < 0 ? "negative" : "positive")+" by a factor of "+result );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		OutputWriter.endStream();
	}
	
	
	public static void parse(String line)
	{
		try
		{
			PositiveWords	pos		= new PositiveWords();
			NegativeWords	neg		= new NegativeWords();
			
			int positive = 0, negative = 0;
			
			ArrayList<String> p = new ArrayList<String>();
			ArrayList<String> n = new ArrayList<String>();
			
			
			String sentences[] = loader.sanitizeMessage(nlp.splitSentences(line));
	
			for (String sent : sentences)
			{
				String tokens[]	= nlp.tokenize(sent);
				String tags[]	= nlp.tag(tokens);
				
				System.out.println(sent);
				
				for (int i = 0; i < tokens.length; i++)
				{
					if (pos.contains(tokens[i]))
					{
						p.add(tokens[i]);
						positive++;
					}
					
					if (neg.contains(tokens[i]))
					{
						n.add(tokens[i]);
						negative++;
					}
				}
			}
			
			System.out.println("-------------------------------------------");
			System.out.println("Positive: " + positive);
			System.out.println(p.toString());
			System.out.println("Negative: " + negative);
			System.out.println(n.toString());
			System.out.println("Total: " + (positive - negative));
			System.out.println("-------------------------------------------");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
