package com.util;

public class StringUtil {

	/**
	 * returns true if value is null or empty string
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return value == null || "".equals(value.trim());
	}

	/**
	 * returns false if value is null or empty string
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}


}
