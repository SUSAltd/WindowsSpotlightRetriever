import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class WindowsSpotlightRetriever {

	
	
	public static void main(String[] args) throws IOException {

		// Windows Spotlight directory
		File spotlightDir = new File(System.getProperty("user.home") + 
				"\\AppData\\Local\\Packages\\"
				+ "Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\"
				+ "LocalState\\Assets");
		
		File destinationDir = null;
		
		switch (args.length) {
			case 0:
				Scanner console = new Scanner(System.in);
				System.out.print("Destination directory path: ");
				destinationDir = new File(console.nextLine()); // initialize
				
				while (!destinationDir.isDirectory()) {
					System.out.print("Directory does not exist. Please get it right this time: ");
					destinationDir = new File(console.nextLine());
				}
				break;
			case 1:
				destinationDir = new File(args[0]);
				if (!destinationDir.isDirectory()) {
					System.out.println("Directory does not exist.");
					System.exit(0);
				}
				break;
			default:
				System.out.println("Only one argument pls");
				System.exit(0);
				break;
		}
		
		int filesCopied = 0;
		for (File f : spotlightDir.listFiles()) {
			File destinationFile = new File(destinationDir + "\\" + f.getName() + ".jpg");
			if (f.length() > 770000 && !destinationFile.exists()) {
				Files.copy(f.toPath(), destinationFile.toPath());
				filesCopied++;
			}
		}
		System.out.println(filesCopied + " files copied.");
	}

}
