package com.cts.bscp.dao;

import java.util.List;

import com.cts.bscp.exception.LAPStoreException;
import com.cts.bscp.model.Lap;

public interface ILapDAO {	
	
	
	String add(Lap lap) throws LAPStoreException;
	boolean delete(String lapcode) throws LAPStoreException;
	Lap get(String lapcode) throws LAPStoreException;
	List<Lap> getAll() throws LAPStoreException;;
	boolean update(Lap lap) throws LAPStoreException, Exception;
	void persist() throws LAPStoreException;
	java.awt.List getAll1() throws LAPStoreException;
}
