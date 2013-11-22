package com.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encode/Decode Tools</br>
 * 14/11/2013
 * @author gang.chen
 * 
 */
public class CodeUtils {
	
	/**
	 * Transform Chinese character to Unicode
	 * @param s Chinese character
	 * @return Unicode character
	 */
	public static String stringToUnicode(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			if (ch > 255)
				str += "\\u" + Integer.toHexString(ch);
			else
				str += "\\" + Integer.toHexString(ch);
		}
		return str;

	}

	/**
	 * Transform Unicode to Chinese character
	 * @param str Unicode character
	 * @return Chinese character
	 */
	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	
	public static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

	/**
	 * Encode
	 * 
	 * @param input
	 * @return
	 */
	public static String encodeURIComponent(String input) {
		if (input == null || "".equals(input)) {
			return input;
		}
		int l = input.length();
		StringBuilder o = new StringBuilder(l * 3);
		try {
			for (int i = 0; i < l; i++) {
				String e = input.substring(i, i + 1);
				if (ALLOWED_CHARS.indexOf(e) == -1) {
					byte[] b = e.getBytes("utf-8");
					o.append(getHex(b));
					continue;
				}
				o.append(e);
			}
			return o.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return input;
	}

	private static String getHex(byte buf[]) {
		StringBuilder o = new StringBuilder(buf.length * 3);
		for (int i = 0; i < buf.length; i++) {
			int n = (int) buf[i] & 0xff;
			o.append("%");
			if (n < 0x10) {
				o.append("0");
			}
			o.append(Long.toString(n, 16).toUpperCase());
		}
		return o.toString();
	}

	/**
	 * Decode
	 * 
	 * @param encodedURI
	 * @return
	 */
	public static String decodeURIComponent(String encodedURI) {
		char actualChar;

		StringBuffer buffer = new StringBuffer();
		int bytePattern, sumb = 0;
		for (int i = 0, more = -1; i < encodedURI.length(); i++) {
			actualChar = encodedURI.charAt(i);
			switch (actualChar) {
			case '%': {
				actualChar = encodedURI.charAt(++i);
				int hb = (Character.isDigit(actualChar) ? actualChar - '0'
						: 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
				actualChar = encodedURI.charAt(++i);
				int lb = (Character.isDigit(actualChar) ? actualChar - '0'
						: 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
				bytePattern = (hb << 4) | lb;
				break;
			}
			case '+': {
				bytePattern = ' ';
				break;
			}
			default: {
				bytePattern = actualChar;
			}
			}

			if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
				sumb = (sumb << 6) | (bytePattern & 0x3f);
				if (--more == 0)
					buffer.append((char) sumb);
			} else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
				buffer.append((char) bytePattern);
			} else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
				sumb = bytePattern & 0x1f;
				more = 1;
			} else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
				sumb = bytePattern & 0x0f;
				more = 2;
			} else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
				sumb = bytePattern & 0x07;
				more = 3;
			} else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
				sumb = bytePattern & 0x03;
				more = 4;
			} else { // 1111110x
				sumb = bytePattern & 0x01;
				more = 5;
			}
		}
		return buffer.toString();
	}

	public static void main(String[] arges) {
//		String t = "[{\"brand\":[1,2,3]},{\"priceStart\":\"0\"},{\"priceEnd\":\"14980\"},{\"order\":\"\"}]";
//		System.out.println(t);
//		System.out.println(encodeURIComponent(t));
//		System.out.println(decodeURIComponent(t));
	}
}