import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
	    		if(OSValidator.isWindows()) {
	    			Runtime.getRuntime().exec("cmd.exe /c .\\chrome\\chrome.exe --user-data-dir=C:\\temp\\ \"http://127.0.0.1:9000/\"" );
	    		} else {
	    			Desktop.getDesktop().browse(new URI("http://127.0.0.1:9000"));
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