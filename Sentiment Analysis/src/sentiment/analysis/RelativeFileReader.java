package sentiment.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RelativeFileReader {
	
	private String filePath;
	
	public RelativeFileReader(String filePath) {
		
	}
	
	public InputStream getResource() {
		try {
			// try relative to current directory
			File file = new File(filePath);
			if ( file.exists() ) {
				return new FileInputStream( file );
			}
			// try the classpath
			return Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
