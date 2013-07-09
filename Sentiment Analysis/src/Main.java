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
		try
		{
			String fileName = "Entire Raw Text.txt";
			BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples" + System.getProperty("file.separator") + fileName));
			String line;
			
			while ((line = in.readLine()) != null)
			{
				parse(line);
			}
			in.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
				
				System.out.println("Sentence: " + sent);
				
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
			
			System.out.println("Positive: " + positive);
			System.out.println(p.toString());
			System.out.println("Negative: " + negative);
			System.out.println(n.toString());
			System.out.println("Total: " + (positive - negative));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
