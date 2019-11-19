

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA256Util {
	private static String salt="william";
	public static String sha256(String data) {
		try {
			data =salt+data;
			byte[] md5 = md5(data.getBytes("utf-8"));
			return toHexString(sha256);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static byte[] md5(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(data);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new byte[]{};
	}
	
	private static String toHexString(byte[] md5) {
		StringBuilder sb = new StringBuilder();
		for(byte b : md5) {
			sb.append(leftPad(Integer.toHexString(b & 0xff), '0', 2));
		}
		return sb.toString();
	}
	
	private static String leftPad(String hex, char c, int size) {
		char[] cs = new char[size];
		Arrays.fill(cs, c);
		System.arraycopy(hex.toCharArray(), 0, cs, cs.length - hex.length(), hex.length());
		return new String(cs);
	}
}