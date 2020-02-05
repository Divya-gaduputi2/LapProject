package com.cts.bscp.dao;

public class IQueryMapper {
	public static final String ADD_LAP_QRY = 
			"INSERT INTO LAPs(lcode,  title, price, pdate) VALUES(?,?,?,?)";
	public static final String MODIFY_LAP_QRY = 
			"UPDATE LAPs SET title=?,price=?,pdate=? WHERE lcode=?";
	public static final String DEL_LAP_QRY = 
			"DELETE FROM LAPs WHERE lcode=?";
	public static final String GET_ALL_LAPS_QRY = 
			"SELECT * FROM LAPs";
	public static final String GET_LAP_QRY = 
			"SELECT * FROM LAPs WHERE lcode=?";
}
