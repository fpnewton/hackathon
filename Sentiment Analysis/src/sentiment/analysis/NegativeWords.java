package sentiment.analysis;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class NegativeWords
{

	private String[] words;


	public NegativeWords()
	{
		try
		{
			String fileName = "Negative Words.txt";
			BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + System.getProperty("file.separator") + "samples" + System.getProperty("file.separator") + fileName));

			StringBuilder text = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null)
			{
				text.append(line + "\n");
			}
			in.close();


			InputStream is = new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "models" + System.getProperty("file.separator") + "en-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(is);
			Tokenizer tokenizer = new TokenizerME(tokenModel);
			words = tokenizer.tokenize(text.toString());
			// for (String s : words)
			// System.out.println(s);
			// System.out.println(words.length);

			is.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public boolean contains(String phrase)
	{
		for (String word : words)
		{
			if (word.compareToIgnoreCase(phrase) == 0)
			{
				return true;
			}
		}

		return false;
	}


	public String[] getWords()
	{
		return words;
	}
}
