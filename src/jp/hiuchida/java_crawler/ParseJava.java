package jp.hiuchida.java_crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseJava {
    static final String S_PACKAGE = "package ";
    static final String S_IMPORT = "import ";
    static final String S_PUBLIC_CLASS = "public class ";
    static final String S_CLASS = "class ";
    static JavaMap javaMap = JavaMap.singleton;
    static JavaMap.JavaInfo javaInfo;
    public static void execute(String filepath) {
    	javaInfo = javaMap.new JavaInfo();
    	javaMap.map.put(filepath, javaInfo);
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                if (line.startsWith(S_PACKAGE)) {
                    parsePackage(line);
                } else if (line.startsWith(S_IMPORT)) {
                    parseImport(line);
                } else if (line.startsWith(S_PUBLIC_CLASS)) {
                    parsePublicClass(line);
                } else if (line.startsWith(S_CLASS)) {
                    parseClass(line);
                }
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
        System.err.println("P " + line);
        javaInfo.packageName = line;
    }
    static void parseImport(String line) {
        line = line.substring(S_IMPORT.length());
        int idx = line.indexOf(";");
        if (idx >= 0) line = line.substring(0, idx);
        line = line.trim();
        System.err.println("I " + line);
        javaInfo.importList.add(line);
        javaMap.importSet.add(line);
    }
    static void parsePublicClass(String line) {
        line = line.substring(S_PUBLIC_CLASS.length());
        int idx = line.indexOf("{");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf(" ");
        if (idx >= 0) line = line.substring(0, idx);
        System.err.println("PC " + line);
        javaInfo.publicClassList.add(line);
        javaMap.fqcnSet.add(javaInfo.packageName + "." + line);
    }
    static void parseClass(String line) {
        line = line.substring(S_CLASS.length());
        int idx = line.indexOf("{");
        if (idx >= 0) line = line.substring(0, idx);
        idx = line.indexOf(" ");
        if (idx >= 0) line = line.substring(0, idx);
        System.err.println("C " + line);
        javaInfo.classList.add(line);
    }
}
