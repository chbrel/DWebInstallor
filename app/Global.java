import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import play.*;

public class Global extends GlobalSettings {

  @Override
  public void onStart(Application app) {
	Logger.info("Start Web Browser...");
	try {
		Desktop.getDesktop().browse(new URI("http://127.0.0.1:9000"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  } 
    
}