package com.example.cmp.datasource;

public class MusicDataSource {
	private static String dataSource = "";
	

	public static String getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public static void setDataSource(String dataSource) {
		MusicDataSource.dataSource = dataSource;
	}
}
