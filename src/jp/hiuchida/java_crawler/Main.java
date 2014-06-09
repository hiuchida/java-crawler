package jp.hiuchida.java_crawler;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
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
		ArrayList<String> importList = new ArrayList<>(JavaMap.importSet);
		Collections.sort(importList);
		System.out.println("-----");
		printList(importList);
		System.out.println("-----");
		//System.out.println("" + JavaMap.singleton.map);
	}
	static void printList(ArrayList<String> list) {
		for (String l : list) {
			System.out.println("" + l);
		}
	}
}
