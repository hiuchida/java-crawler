package jp.hiuchida.java_crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class JavaMap {
	static Log log = new LogImpl();
	public static JavaMap singleton = new JavaMap();
	public static HashMap<String,JavaInfo> map = new HashMap<>();
	public class JavaInfo {
		public String fileName;
		public String packageName;
		public ArrayList<String> importList = new ArrayList<>();
		public ArrayList<String> publicClassList = new ArrayList<>();
		public ArrayList<String> classList = new ArrayList<>();
		public ArrayList<String> publicInterfaceList = new ArrayList<>();
		public String toString() {
			return "{fileName=" + fileName
				+ ", packageName=" + packageName
				+ ", importList=" + importList
				+ ", publicClassList=" + publicClassList
				+ ", classList=" + classList
				+ ", publicInterfaceList=" + publicInterfaceList
				+ "}";
		}
	}
	public static HashMap<String,Integer> importMap = new HashMap<>();
	public static void incImportMap(String s) {
		if (importMap.get(s) == null) {
			importMap.put(s, 1);
		} else {
			importMap.put(s, importMap.get(s) + 1);
		}
	}
	public static StringArray getImportArray() {
		StringArray sa = new StringArray(importMap.keySet());
		Collections.sort(sa);
		return sa;
	}
	public static HashSet<String> fqcnSet = new HashSet<>();
	public static StringArray getFqcnArray() {
		StringArray sa = new StringArray(fqcnSet);
		Collections.sort(sa);
		return sa;
	}
}
