import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.lang.Process;

// import controllers.Application;

import models.OSValidator;
import play.*;

public class Global extends GlobalSettings {

	class StartThread extends Thread {
		StartThread() {

		}

		public void run() {
			Logger.info("Waiting a second...");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Logger.info("Start Web Browser...");

			try {
				String options = "";
				try {
					BufferedReader br = new BufferedReader(new FileReader(
							"./resources/options.txt"));
					try {
						options = br.readLine();

						br.close();
					} catch (IOException ioe) {
						System.out.println("/!\\ Error while reading file \n");
					}
				} catch (FileNotFoundException fnfe) {
					System.out.println("/!\\ File doesn't exists\n");
				}
				if (options == null) {
					options = "";
				}
				if (OSValidator.isWindows()) {
					controllers.Application.BROWSER = Runtime
							.getRuntime()
							.exec("cmd.exe /c .\\chrome\\chrome.exe " + options + " --user-data-dir=C:\\temp\\ \"http://127.0.0.1:9000/\"");
				} else {
					Desktop.getDesktop().browse(
							new URI("http://127.0.0.1:9000"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onStart(Application app) {
		StartThread st = new StartThread();
		st.start();
	}

}