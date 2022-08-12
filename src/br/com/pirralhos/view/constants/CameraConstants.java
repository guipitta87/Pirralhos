package br.com.pirralhos.view.constants;

import java.io.IOException;
import java.net.InetAddress;

public class CameraConstants {
	
	
	public static final String ASX_FILE = "<ASX version =\"3.0\">" + "<TITLE>%s</TITLE>"
			+ "<ENTRY>" + "<REF HREF=\"http://%s:1234/%s\" />"
			+ "</ENTRY>" + "</ASX>";
	public static final String[] COMMAND = new String[] {"cmd.exe", "/c", "start c:\\Program Files\\VideoLAN\\VLC\\vlc.exe", "/r:D:\\TST\\script.txt"};        
	
	public static void main(String[] args) throws IOException {
		  String ip = InetAddress.getLocalHost().getHostAddress();

		    	System.out.println(ip);
	}
}
