import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class SampleLoader
{
	private String basePath;
	
	
	public SampleLoader(String relativePath)
	{
		basePath = relativePath + System.getProperty("file.separator");
	}
	
	
	public String loadSample(String path) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(basePath + path));
		StringBuilder sampleText = new StringBuilder();
		String line;
		
		while((line = in.readLine()) != null)
		{
		    sampleText.append(line);
		}
		
		in.close();
		
		
		return sampleText.toString();
	}
	
	
	public String[] sanitizeMessage(String input[])
	{
		ArrayList<String>	temp	= new ArrayList<String>();
		boolean				found	= false;
		
		for (String sentence : input)
		{				
			if (!found && sentence.toLowerCase().contains("original message"))
			{
				found = true;
				continue;
			}
			
			if (!found)
			{
				continue;
			}
			
			temp.add(sanitizeSentence(sentence));
		}
		
		
		return temp.toArray(new String[0]);
	}
	
	
	public String sanitizeSentence(String input)
	{
		String temp = input.replaceAll("\\<.*?>",""); // Remove HTML
		
		temp = temp.replaceAll("([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})", ""); // Remove phone numbers
		temp = temp.replaceAll("([^.@\\s]*)(\\.[^.@\\s]+)*@([^.@\\s]+\\.)+([^.@\\s]+)", ""); // Remove Email addresses
		temp = temp.replaceAll("http.*?\\s", " "); // Remove URLs
		
		
		return temp;
	}
}
