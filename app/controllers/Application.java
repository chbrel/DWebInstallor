package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import play.*;
import play.libs.Comet;
import play.mvc.*;
import play.mvc.Http.*;

import views.html.*;

import models.*;

public class Application extends Controller {
  
	public static final String YEAR_FILE_PATH = "." + File.separator + "resources" + File.separator + "year.txt";
	public static final String GAMES_PATH = "." + File.separator + "resources" + File.separator + "CDContenu" + File.separator + "LesLogiciels" + File.separator;
	public static final String GAMES_INFOS_PATH = "." + File.separator + "resources" + File.separator + "CDContenu" + File.separator + "GamesInfos" + File.separator;
	
  public static int getYear() {
	  Integer year = 0;
	  
	  try{
          BufferedReader br = new BufferedReader(new FileReader(YEAR_FILE_PATH));
          String line;
          try{
              line = br.readLine();

              year = Integer.parseInt(line);

              br.close();
          }
          catch(IOException ioe){
          System.out.println("/!\\ Error while reading file \n");}
      }
      catch(FileNotFoundException fnfe){
          System.out.println("/!\\ File doesn't exists\n");
      }
      
      return year;
  }
  
  public static void copyInfosFiles() {
	  boolean doCopy = true;
	  
	  File gamesInfosFolder = new File(GAMES_INFOS_PATH);
	  
	  if(gamesInfosFolder.exists() && gamesInfosFolder.isDirectory()) {
		  if(gamesInfosFolder.listFiles().length > 0) {
			  doCopy = false;
		  }
	  }
	  
	  if(doCopy) {
		  // création de répertoire et copie des infos.xml
		  
		  
		  // FileUtils.copy(new File("." + File.separator + "lib" + File.separator + "mac"), new File(this.getInstallationFolder() + File.separator + "lib" + File.separator));
	  }
  }
	
  public static Result index() {
	  int year = getYear();
      
	  copyInfosFiles();
	  
      return ok(index.render("Bienvenue dans l'installation du CD DeViNT " + year + " !", year));
  }
  
  public static Result licence() {
	 int year = getYear();
	  
	 return ok(licence.render("Les projets DeViNT sont sous licence !", year));
  }
  
  public static Result config() {
	  int year = getYear();
	  
	  String defaultInstallFolder = "";
	  
	  if(OSValidator.isWindows()) {
			defaultInstallFolder = "C:" + File.separator + "Program Files" + File.separator + "DeViNT" + File.separator;
			if(OSValidator.isWindowsSeven()) {
				defaultInstallFolder = System.getProperty("user.home") + File.separator + "DeViNT" + File.separator;
			}
		} else if(OSValidator.isUnix()) {
			defaultInstallFolder = File.separator + "usr" + File.separator + "DeViNT" + File.separator;
		} else if(OSValidator.isMac()) {
			defaultInstallFolder = File.separator + "Applications" + File.separator + "DeViNT" + File.separator;
		}
	  
	  return ok(config.render("Configuration de l'installation", year, defaultInstallFolder));
  }
  
  public static Result gamechoices(String sortedMethod) {
	  int year = getYear();
	  
	  RequestBody body = request().body();
	  Map<String,String[]> args = body.asFormUrlEncoded();
	  
	  if(args != null && args.keySet().contains("installfolder")) {
		  session("installfolder", args.get("installfolder")[0]);
	  }
	  
	  ArrayList<Game> games = Game.getAll(GAMES_PATH);
	  
	  Collections.sort(games, new YearGameSortComparator());
	  String htmlGameList = getGamesYearRepresentation(games);
	  
	  if(sortedMethod.equals("category")) {
		  htmlGameList = getGamesCategoryRepresentation(getSortedbyCategory(games));
	  }
	  
	  return ok(gamechoices.render("Choix des projets à installer", year, sortedMethod, htmlGameList));
  }
  
  public static TreeMap<GameCategory, ArrayList<Game>> getSortedbyCategory(ArrayList<Game> games) {
	  
	  TreeMap<GameCategory, ArrayList<Game>> catGames = new TreeMap<GameCategory, ArrayList<Game>>();
	  
	    for(Game g: games) {
			for(GameCategory gameCat: g.getGameCategories()) {
				if(!catGames.containsKey(gameCat)) {
					catGames.put(gameCat, new ArrayList<Game>());
				}
				catGames.get(gameCat).add(g);
			}
		}
		
		return catGames;
  }
  
  public static String getGamesYearRepresentation(ArrayList<Game> games) {
	  String html = "";
	  
	  
	  int currentYear = 0;
	  
	  for(Game g : games) {
		if(currentYear != g.getAnnee()) {
			if(currentYear != 0) {
				html += "</ul></li>";
			}
			html += "<li class=\"active\">";
			html += "<a><label class=\"checkbox\"><input type=\"checkbox\" class=\"game\" id=\"game_" + g.getAnnee() + "_all\" onchange=\"javascript:selectAll('game_" + g.getAnnee() + "')\" /></label>&nbsp;&nbsp;" + g.getAnnee() + "</a>";
			html += "<ul>";
			currentYear = g.getAnnee();
		}
	  
		html += "<li><label class=\"checkbox\">";
		html += "<input type=\"checkbox\" class=\"game game_" + g.getAnnee() + "\" name=\"games\" value=\"" + g.getGameRep() + "\" /> " + g.getTitle();
		html += "</label></li>";
	  }
	  html += "</ul></li>";
	  
	  return html;
  }
  
  public static String getGamesCategoryRepresentation(TreeMap<GameCategory, ArrayList<Game>> catGames) {
	  String html = "";
	  
	  for(GameCategory gameCat: catGames.keySet()) {
		  String clearCatName = gameCat.toString().toLowerCase().replaceAll(" ", "");
			html += "<li class=\"active\">";
			html += "<a><label class=\"checkbox\"><input type=\"checkbox\" class=\"game\" id=\"game_" + clearCatName + "_all\" onchange=\"javascript:selectAll('game_" + clearCatName + "')\" /></label>&nbsp;&nbsp;" + gameCat + "</a>";
			html += "<ul>";
			
			for(Game g: catGames.get(gameCat)) {
				html += "<li><label class=\"checkbox\">";
				html += "<input type=\"checkbox\" class=\"game game_" + clearCatName + "\" name=\"games\" value=\"" + g.getGameRep() + "\" /> " + g.getTitle();
				html += "</label></li>";
			}
		
			html += "</ul></li>";
	  }
	  
	  
	  return html;
  }
  
  public static Result installation() {
	  int year = getYear();
	  
	  Comet comet = new Comet("parent.cometMessage") {
		    public void onConnected() {
		      sendMessage("kiki");
		      sendMessage("foo");
		      sendMessage("bar");
		      close();
		    }
		  };
		  
		  RequestBody body = request().body();
		  Map<String,String[]> args = body.asFormUrlEncoded();
		  
		  String test = "";
		  
		  if(args != null && args.keySet().contains("games")) {  
			  String[] selectedGames = args.get("games");
			  for(String sg : selectedGames) {
				  test += sg + "<br /><br />"; 
			  }
		  }
		  
		return ok(installation.render("Installation en cours", year, comet, test));
  }
}