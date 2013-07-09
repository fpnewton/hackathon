import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;


public class NLP
{
	private POSModel posModel;
	
	
	public NLP()
	{
		posModel = new POSModelLoader().load(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "models" + System.getProperty("file.separator") + "en-pos-maxent.bin"));
	}
	
	
	public String[] splitSentences(String input) throws InvalidFormatException, IOException
	{
		InputStream			is			= new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "models" + System.getProperty("file.separator") + "en-sent.bin");
		SentenceModel		model		= new SentenceModel(is);
		SentenceDetectorME	sdetector	= new SentenceDetectorME(model);

		String				sentences[]	= sdetector.sentDetect(input);

		is.close();
		
		
		return sentences;
	}
	
	
	public String[] tokenize(String input) throws InvalidFormatException, IOException
	{
		InputStream		is			= new FileInputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "models" + System.getProperty("file.separator") + "en-token.bin");
		TokenizerModel	model		= new TokenizerModel(is);
		Tokenizer		tokenizer	= new TokenizerME(model);
 
		String			tokens[]	= tokenizer.tokenize(input);
 
		is.close();
		
		
		return tokens;
	}
	
	
	public String[] tag(String sentence[]) throws IOException
	{
		POSTaggerME tagger = new POSTaggerME(posModel);
		
		return tagger.tag(sentence);
	}
}
