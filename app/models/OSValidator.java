package models;

/**
 * From: http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
 * 
 * @author mkyong
 *
 */
public class OSValidator{
	 
	public static void main(String[] args)
	{
		if(isWindows()){
			System.out.println("This is Windows");
		}else if(isMac()){
			System.out.println("This is Mac");
		}else if(isUnix()){
			System.out.println("This is Unix or Linux");
		}else{
			System.out.println("Your OS is not support!!");
		}
	}
 
	public static boolean isWindows(){
 
		String os = System.getProperty("os.name").toLowerCase();
		//windows
	    return (os.indexOf( "win" ) >= 0); 
 
	}
	
	public static boolean isWindowsXP(){
		 
		String os = System.getProperty("os.name").toLowerCase();
		//windows XP
	    return (os.indexOf( "win" ) >= 0) && (os.indexOf( "xp" ) >= 0); 
 
	}
	
	public static boolean isWindowsSeven(){
		 
		String os = System.getProperty("os.name").toLowerCase();
		//windows 7
	    return (os.indexOf( "win" ) >= 0) && (os.indexOf( "7" ) >= 0); 
 
	}
	
	public static boolean isWindowsEight(){
		 
		String os = System.getProperty("os.name").toLowerCase();
		//windows 8
	    return (os.indexOf( "win" ) >= 0) && (os.indexOf( "8" ) >= 0); 
 
	}
	
	public static boolean isWindowsNT(){
		 
		String os = System.getProperty("os.name").toLowerCase();
		//windows NT (unknown)
	    return (os.indexOf( "win" ) >= 0) && (os.indexOf( "nt" ) >= 0); 
 
	}
 
	public static boolean isMac(){
 
		String os = System.getProperty("os.name").toLowerCase();
		//Mac
	    return (os.indexOf( "mac" ) >= 0); 
 
	}
 
	public static boolean isUnix(){
 
		String os = System.getProperty("os.name").toLowerCase();
		//linux or unix
	    return (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0);
 
	}
}