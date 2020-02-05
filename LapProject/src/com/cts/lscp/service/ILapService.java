package com.cts.bscp.service;

import java.util.List;

import com.cts.bscp.exception.LAPStoreException;
import com.cts.bscp.model.Lap;

public interface ILapService {	
	String add(Lap lap) throws LAPStoreException;
	boolean delete(String tvcode) throws LAPStoreException;
	Lap get(String tvcode) throws LAPStoreException;
	List<Lap> getAll() throws LAPStoreException;;
	boolean update(Lap lap) throws LAPStoreException, Exception;
	
}
