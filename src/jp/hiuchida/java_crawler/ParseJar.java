package jp.hiuchida.java_crawler;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ParseJar {
	static Log log = new LogImpl();
    static JavaMap javaMap = JavaMap.singleton;
    static JavaMap.JavaInfo javaInfo;
    public static void execute(String filepath, String filename) {
        HashSet<String> fqcnSet = new HashSet<>();
    	javaInfo = javaMap.new JavaInfo();
    	javaInfo.fileName = filename;
    	javaMap.map.put(filepath, javaInfo);
        try (JarFile jf = new JarFile(filepath)) {
			for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements(); ) {
			    JarEntry je = e.nextElement();
			    String name = je.getName();
			    if (name.endsWith(".class")) {
			    	name = name.substring(0, name.length() - 6);
			    	name = name.replaceAll("/", ".");
			    	log.trace("C " + name);
			    	fqcnSet.add(name);
			    	javaMap.fqcnSet.add(name);
			    }
			}
	        StringArray fqcn = new StringArray(fqcnSet);
	        Collections.sort(fqcn);
	        fqcn.save("jarinfo/" + filename + ".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
