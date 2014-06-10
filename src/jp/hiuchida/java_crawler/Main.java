package jp.hiuchida.java_crawler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
	static Log log = new LogImpl();
	public static void main(String[] args) {
		String filepath = "src";
		if (args.length > 0) {
			filepath = args[0];
		}
		FileList.execute(filepath);
		ArrayList<String> fqcnList = new ArrayList<>(JavaMap.fqcnSet);
		Collections.sort(fqcnList);
		System.out.println("-----");
		printList(fqcnList);
		ArrayList<String> importList = new ArrayList<>(JavaMap.importMap.keySet());
		Collections.sort(importList);
		System.out.println("-----");
		printMap(importList, JavaMap.importMap);
		System.out.println("-----");
		//System.out.println("" + JavaMap.singleton.map);
	}
	static void printList(ArrayList<String> list) {
		for (String l : list) {
			System.out.println("" + l);
		}
	}
	static void printMap(ArrayList<String> list, HashMap<String,Integer> map) {
		for (String l : list) {
			if (map.get(l) > 1) {
				System.out.println("" + l + " x" + map.get(l));
			} else {
				System.out.println("" + l);
			}
		}
	}
}
