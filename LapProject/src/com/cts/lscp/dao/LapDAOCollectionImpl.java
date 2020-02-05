package com.cts.bscp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cts.bscp.exception.LAPStoreException;
import com.cts.bscp.model.Lap;

public abstract class LapDAOCollectionImpl implements ILapDAO {
	
	private Map<String, Lap> laps;
	
	public LapDAOCollectionImpl() {
		laps = new TreeMap<>();
	}
	
	@Override
	public String add(Lap lap) throws LAPStoreException {
		String lapcode = null;
		if (lap != null) {
			lapcode = lap.getlapcode();
			laps.put(lapcode, lap);	
		}
		return lapcode;
	}
	
	@Override
	public boolean delete(String lapcode) throws LAPStoreException {
		boolean isDone = false;
		if (lapcode != null) {
			laps.remove(lapcode);
			isDone = true;
		}
		return isDone;
	}
	
	@Override
	public Lap get(String lapcode) throws LAPStoreException {
		return laps.get(lapcode);
	}
	

	@Override
	public List<Lap> getAll() throws LAPStoreException {
		return new ArrayList<>(laps.values());
	}
	
	public boolean update(Lap lap) throws LAPStoreException {
		boolean isDone = false;
		if (lap != null) {
			String bcode = lap.getlapcode();
			laps.replace(bcode, lap);
			
		}
		return isDone;
	}


	
}
