package operators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
	public static void createInputFile(String text){
		saveTextToFile(text, "TaKIPI/in.txt");
		try {
			runCommand();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void saveTextToFile(String text, String fileName){
		try{
		    PrintWriter writer = new PrintWriter(fileName, "CP1250");
		    writer.print(text);
		    writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static void runCommand() throws IOException, InterruptedException{
		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/k");
//		processBuilder.redirectOutput(Redirect.INHERIT);
//		processBuilder.redirectError(Redirect.INHERIT);
		Process p = processBuilder.start();
		
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(p.getOutputStream()), true);
		printWriter.println("cd TaKIPI");
		printWriter.println("takipi.exe -CP1250 -it TXT -i in.txt -o out.xml");
		printWriter.println("exit");
		
		p.waitFor();
	}
	
	public static void removeSecondLine(){
		File file = new File("TaKIPI/out.xml");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		ArrayList<String> coll = new ArrayList<String>();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(scanner.nextLine());
		scanner.nextLine();
		while (scanner.hasNextLine()) {
		    String line = scanner.nextLine();
		    stringBuilder.append(line);
		}
		scanner.close();

		saveTextToFile(stringBuilder.toString(), "TaKIPI/out.xml");
	}
}
