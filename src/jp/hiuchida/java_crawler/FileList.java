package jp.hiuchida.java_crawler;

import java.io.File;

public class FileList {
	public static void execute(String filepath) {
		execute(new File(filepath));
	}
    static void execute(File dir) {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                System.err.println("D " + f);
                execute(f);
            } else if (f.isFile()) {
                System.err.println("F " + f);
                if (f.getName().endsWith(".java")) {
                	ParseJava.execute(f.getPath());
                }
            }
        }
    }
}
