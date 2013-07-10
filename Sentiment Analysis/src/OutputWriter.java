import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class OutputWriter {
	 
	private static BufferedWriter bw;
	
	public static void openStream() {
		String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "results.txt";
		File file = new File(filePath);
		 
		try {
		// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(String content) {
		try {
			bw.write(content);
			bw.newLine();
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void endStream() {
		try {
			if (bw != null)
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
