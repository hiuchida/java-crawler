package jp.hiuchida.java_crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseJava {
	static Log log = new LogImpl();
    static final String S_PACKAGE = "package ";
    static final String S_IMPORT = "import ";
    static final String S_IMPORT_STATIC = "import static ";
    static final String S_PUBLIC_CLASS = "public class ";
    static final String S_CLASS = "class ";
    static final String S_PUBLIC_INTERFACE = "public interface ";
    static final String S_INTERFACE = "interface ";
    static JavaMap javaMap = JavaMap.singleton;
    static JavaMap.JavaInfo javaInfo;
    public static void execute(String filepath, String filename) {
    	javaInfo = javaMap.new JavaInfo();
    	javaInfo.fileName = filename.substring(0, filename.length() - 5);
    	javaMap.map.put(filepath, javaInfo);
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                if (line.startsWith(S_PACKAGE)) {
                    parsePackage(line);
                } else if (line.startsWith(S_IMPORT_STATIC)) {
                    parseImportStatic(line);
                } else if (line.startsWith(S_IMPORT)) {
                    parseImport(line);
                /*} else if (line.startsWith(S_PUBLIC_CLASS)) {
                    parsePublicClass(line);
                } else if (line.startsWith(S_CLASS)) {
                    parseClass(line);
                } else if (line.startsWith(S_PUBLIC_INTERFACE)) {
                    parsePublicInterface(line);
                } else if (line.startsWith(S_INTERFACE)) {
                    parseInterface(line);
                */}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void parsePackage(String line) {
        line = line.substring(S_PACKAGE.length());
        int idx = line.indexOf(";");
        if (idx >= 0) line = line.substring(0, idx);
        line = line.trim();
        log.trace("P " + line);
        javaInfo.packageName = line;
        javaMap.fqcnSet.add(javaInfo.packageName + "." + javaInfo.fileName);
    }
    static void parseImportStatic(String line) {
        line = line.substring(S_IMPORT_STATIC.length());
        int idx = line.indexOf(";");
        if (idx >= 0) line = line.substring(0, idx);
        line = line.trim();
        log.trace("I " + line);
        javaInfo.importList.add(line);
        javaMap.incImportMap(line);
    }
    static void parseImport(String line) {
        line = line.substring(S_IMPORT.length());
        int idx = line.indexOf(";");
        if (idx >= 0) line = line.substring(0, idx);
        line = line.trim();
        log.trace("I " + line);
        javaInfo.importList.add(line);
        javaMap.incImportMap(line);
    }
    static void parsePublicClass(String line) {
        line = line.substring(S_PUBLIC_CLASS.length());
        int idx = line.indexOf("{");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf("<");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf(" ");
        if (idx >= 0) line = line.substring(0, idx);
        log.trace("PC " + line);
        if (!javaInfo.fileName.equals(line)) {
        	log.warn("fileName=" + javaInfo.fileName + ", publicClass=" + line);
        }
        javaInfo.publicClassList.add(line);
        //javaMap.fqcnSet.add(javaInfo.packageName + "." + line);
    }
    static void parseClass(String line) {
        line = line.substring(S_CLASS.length());
        int idx = line.indexOf("{");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf("<");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf(" ");
        if (idx >= 0) line = line.substring(0, idx);
        log.trace("C " + line);
        javaInfo.classList.add(line);
    }
    static void parsePublicInterface(String line) {
        line = line.substring(S_PUBLIC_INTERFACE.length());
        int idx = line.indexOf("{");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf("<");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf(" ");
        if (idx >= 0) line = line.substring(0, idx);
        log.trace("PI " + line);
        if (!javaInfo.fileName.equals(line)) {
        	log.warn("fileName=" + javaInfo.fileName + ", publicInterface=" + line);
        }
        javaInfo.publicInterfaceList.add(line);
        //javaMap.fqcnSet.add(javaInfo.packageName + "." + line);
    }
    static void parseInterface(String line) {
        line = line.substring(S_INTERFACE.length());
        int idx = line.indexOf("{");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf("<");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf(" ");
        if (idx >= 0) line = line.substring(0, idx);
        log.trace("PI " + line);
        if (!javaInfo.fileName.equals(line)) {
        	log.warn("fileName=" + javaInfo.fileName + ", publicInterface=" + line);
        }
        javaInfo.publicInterfaceList.add(line);
        //javaMap.fqcnSet.add(javaInfo.packageName + "." + line);
    }
}
