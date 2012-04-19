/**
 * THERE IS NO WARRANTY FOR THE PROGRAM, TO THE EXTENT PERMITTED BY APPLICABLE LAW. 
 * EXCEPT WHEN OTHERWISE STATED IN WRITING THE COPYRIGHT HOLDERS AND/OR OTHER PARTIES 
 * PROVIDE THE PROGRAM “AS IS” WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED OR 
 * IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE. THE ENTIRE RISK AS TO THE QUALITY AND 
 * PERFORMANCE OF THE PROGRAM IS WITH YOU. SHOULD THE PROGRAM PROVE DEFECTIVE, YOU 
 * ASSUME THE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION.
 */

package models;

import java.io.*;

/**
 * 
 * From: http://forum.hardware.fr/hfr/Programmation/Java/peut-repertoire-entier-sujet_44879_1.htm
 * 
 * @author Benou
 * 
 * Ptites modifs pour le projet: @author Christian Brel @author Romaric Pighetti
 * 
 */
public class FileUtils {

	/**
	 * @author Christian Brel
	 * @author Romaric Pighetti
	 */
	public static void write(String path, String content) {
//		iv.concat("Ecriture de " + path);
		try {
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(content);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void copy(final InputStream inStream, final OutputStream outStream, final int bufferSize) throws IOException {
		final byte[] buffer = new byte[bufferSize];
		int nbRead;
		while ((nbRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, nbRead);
		}
	}

	public static void copyDirectory(final File from, final File to) throws IOException {
//		iv.concat("-- Copie de répertoire: de " + from.getAbsolutePath() + " vers " + to.getAbsolutePath() + " --");
		
		if (! to.exists()) {
			to.mkdir();
		}
		final File[] inDir = from.listFiles();
		for (int i = 0; i < inDir.length; i++) {
			final File file = inDir[i];
			copy(file, new File(to, file.getName()));
		}
	}
	
	public static void copyFile(final File from, final File to) throws IOException {
//		iv.concat("Copie de fichier: de " + from.getAbsolutePath() + " vers " + to.getAbsolutePath());
		
		final InputStream inStream = new FileInputStream(from);
		final OutputStream outStream = new FileOutputStream(to);
		if (from.length() > 0){ 
			copy(inStream, outStream, (int) Math.min(from.length(), 4*1024));
		}
		inStream.close();
		outStream.close();
	}
	
	public static void copy(final File from, final File to) throws IOException {
		if (from.isFile()) {
			copyFile(from, to);
		} else if (from.isDirectory()){
			copyDirectory(from, to);
		} else {
			throw new FileNotFoundException(from.toString() + " does not exist" );
		}
	}

	/**
	 * @author Christian Brel
	 * @author Romaric Pighetti
	 */
	public static void rmDir(File dir) {
		if (dir.isDirectory()) {
			for (File f : dir.listFiles()) {
				if (f.isDirectory()) {
					rmDir(f);
				} else {
					f.delete();
				}
			}
		}
		dir.delete();
	}
}