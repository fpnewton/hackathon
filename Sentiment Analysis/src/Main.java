

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			SampleLoader	loader	= new SampleLoader(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples");
			NLP				nlp		= new NLP();
			
			
			String sentences[] = loader.sanitizeMessage(nlp.splitSentences(loader.loadSample("01.txt")));

			for (String sent : sentences)
			{
				String tokens[]	= nlp.tokenize(sent);
				String tags[]	= nlp.tag(tokens);
				
				System.out.println("Sentence: " + sent);
				System.out.println("Tokens:");
				
				for (int i = 0; i < tokens.length; i++)
				{
					System.out.print("[" + tokens[i] + ", " + tags[i] + "] ");
				}
				
				System.out.println();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
