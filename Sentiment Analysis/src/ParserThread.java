import java.util.ArrayList;

import sentiment.analysis.NegativeWords;
import sentiment.analysis.PositiveWords;


public class ParserThread implements Runnable
{
	SampleLoader	loader;
	NLP				nlp;
	
	String			parseLine;
	int positive = 0, negative = 0;
	int numWords = 0;
	
	Thread t;
	

	public ParserThread(String input)
	{
		loader	= new SampleLoader(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples");
		nlp		= new NLP();
		
		parseLine = input;
		
		t = new Thread(this, "Parser Thread");
	    t.start(); // Start the thread
	}


	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		parse(parseLine);

	}
	
	private void parse(String line)
	{
		try
		{
			PositiveWords	pos		= new PositiveWords();
			NegativeWords	neg		= new NegativeWords();
			
			ArrayList<String> p = new ArrayList<String>();
			ArrayList<String> n = new ArrayList<String>();
			
			
			String sentences[] = loader.sanitizeMessage(nlp.splitSentences(line));
	
			for (String sent : sentences)
			{
				String tokens[]	= nlp.tokenize(sent);
				String tags[]	= nlp.tag(tokens);
				
				OutputWriter.writeToFile(sent);
				
				for (int i = 0; i < tokens.length; i++)
				{
					if (pos.contains(tokens[i]))
					{
						p.add(tokens[i]);
						positive++;
						//System.out.println(tokens[i]+" ("+tags[i]+")");
					}
					
					if (neg.contains(tokens[i]))
					{
						n.add(tokens[i]);
						negative++;
						//System.out.println(tokens[i]+" ("+tags[i]+")");
					}
				}
			}
			
			OutputWriter.writeToFile("-------------------------------------------");
			OutputWriter.writeToFile("Positive: " + positive);
			OutputWriter.writeToFile(p.toString());
			OutputWriter.writeToFile("Negative: " + negative);
			OutputWriter.writeToFile(n.toString());
			OutputWriter.writeToFile("Total: " + (positive - negative));
			OutputWriter.writeToFile("-------------------------------------------");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
