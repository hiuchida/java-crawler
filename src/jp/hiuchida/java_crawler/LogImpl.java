package jp.hiuchida.java_crawler;

public class LogImpl implements Log {
	public void warn(String m) {
		System.err.println("!WARN: " + m);
	}
	public void trace(String m) {
        System.err.println(m);
	}
}
