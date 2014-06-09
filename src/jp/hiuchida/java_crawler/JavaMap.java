package jp.hiuchida.java_crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class JavaMap {
	public static JavaMap singleton = new JavaMap();
	public static HashMap<String,JavaInfo> map = new HashMap<>();
	public class JavaInfo {
		public String packageName;
		public ArrayList<String> importList = new ArrayList<>();
		public ArrayList<String> publicClassList = new ArrayList<>();
		public ArrayList<String> classList = new ArrayList<>();
		public String toString() {
			return "{packageName=" + packageName
				+ ", importList=" + importList
				+ ", publicClassList=" + publicClassList
				+ ", classList=" + classList
				+ "}";
		}
	}
	public static HashSet<String> importSet = new HashSet<>();
	public static HashSet<String> fqcnSet = new HashSet<>();
}
