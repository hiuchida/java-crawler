package jp.hiuchida.java_crawler;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	static Log log = new LogImpl();
	public static void main(String[] args) {
		String filepath = "src";
		if (args.length > 0) {
			filepath = args[0];
		}
		FileList.execute(filepath);
		StringArray fqcnList = JavaMap.getFqcnArray();
		System.out.println("fqcnList:" + fqcnList);
		StringArray importList = JavaMap.getImportArray();
		System.out.println("importList:" + importList);
	}
	static void printList(ArrayList<String> list) {
		for (String s : list) {
			System.out.println("" + s);
		}
	}
	static void printMap(ArrayList<String> list, HashMap<String,Integer> map) {
		for (String s : list) {
			if (map.get(s) > 1) {
				System.out.println("" + s + " x" + map.get(s));
			} else {
				System.out.println("" + s);
			}
		}
	}
}
