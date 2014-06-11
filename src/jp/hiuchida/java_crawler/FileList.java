package jp.hiuchida.java_crawler;

import java.io.File;

public class FileList {
	static Log log = new LogImpl();
	public static void execute(String filepath) {
		execute(new File(filepath));
	}
    static void execute(File dir) {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                log.trace("D " + f);
                execute(f);
            } else if (f.isFile()) {
            	log.trace("F " + f);
                if (f.getName().endsWith(".java")) {
                	ParseJava.execute(f.getPath(), f.getName());
                } else if (f.getName().endsWith(".jar")) {
                	ParseJar.execute(f.getPath(), f.getName());
                }
            }
        }
    }
}
