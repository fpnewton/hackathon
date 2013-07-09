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
			
			while (true)
			{
				ParserThread t1 = null;
				ParserThread t2 = null;
				ParserThread t3 = null;
				ParserThread t4 = null;
				ParserThread t5 = null;
				ParserThread t6 = null;
				ParserThread t7 = null;
				ParserThread t8 = null;
				
				if ((line = in.readLine()) != null)
				{
					t1 = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					t2 = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					t3 = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					t4 = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					t5 = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					t6 = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					t7 = new ParserThread(line);
				}
				
				if ((line = in.readLine()) != null)
				{
					t8 = new ParserThread(line);
				}
				
				while ((t1 != null && t1.t.isAlive()) || (t2 != null && t2.t.isAlive()) || (t3 != null && t3.t.isAlive()) || (t4 != null && t4.t.isAlive()) || (t5 != null && t5.t.isAlive()) || (t6 != null && t6.t.isAlive()) || (t7 != null && t7.t.isAlive()) || (t8 != null && t8.t.isAlive())) {}
				
				
				if (t1 == null && t2 == null && t3 == null && t4 == null && t5 == null && t6 == null && t7 == null && t8 == null)
				{
					break;
				}
				
				//parse(line);
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
