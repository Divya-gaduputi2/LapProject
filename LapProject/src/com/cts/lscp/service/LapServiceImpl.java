package com.cts.bscp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cts.bscp.dao.ILapDAO;
import com.cts.bscp.exception.LAPStoreException;
import com.cts.bscp.model.Lap;

public abstract class LapServiceImpl implements ILapService {
	
	private ILapDAO lapDao;

	public ILapDAO getDAO(){
		return lapDao;
	}
	
	public LapServiceImpl() throws LAPStoreException {
		lapDao = new LapDAOConnectionImpl();
		
	}
	
	public boolean isValidlapcode(String lapcode){
		
		
		Pattern lapcodePattern = Pattern.compile("[A-Z]\\d{3}");
		Matcher lapcodeMatcher = lapcodePattern.matcher(lapcode);
		
		return lapcodeMatcher.matches();
	}
	
	public boolean isValidcompanytitle(String companytitle){
		
		Pattern companytitlePattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher companytitleMatcher = companytitlePattern.matcher(companytitle);
		
		return companytitleMatcher.matches();
	}
	
	public boolean isValidPrice(double price){
		
		return price>=10000 && price<=500000;
	}
	
	public boolean isValidPublishDate(LocalDate publishDate){
		
		LocalDate today = LocalDate.now();
		
		return today.isAfter(publishDate) || publishDate.equals(today);
	}
	
	public boolean isValidtv(Lap lap) throws LAPStoreException{
		boolean isValid=false;
		
		List<String> errMsgs = new ArrayList<>();
		
		if(!isValidlapcode(lap.getlapcode()))
			errMsgs.add("lapcode should start with a capital alphabet followed by 3 digits");
		
		if(!isValidcompanytitle(lap.getCompanytitle()))
			errMsgs.add("companytitle must start with capital and must be in between 4 to 20 chars in length");
		
		if(!isValidPrice(lap.getPrice()))
			errMsgs.add("Price must be between INR.5 and INR.5000");
		
		if(!isValidPublishDate(lap.getReleaseDate()))
			errMsgs.add("Publish Date should not be a future date");
		
		if(errMsgs.size()==0)
			isValid=true;
		else
			throw new LAPStoreException(errMsgs.toString());
		
		return isValid;
	}


	@Override
	public String add(Lap lap) throws LAPStoreException {
		String lapcode=null;
		if(lap!=null && isValidlap(lap)){
			lapcode=lapDao.add(lap);
		}
		return lapcode;
	}

	private boolean isValidlap(Lap lap) {
	
		return false;
	}

	@Override
	public boolean delete(String lapcode) throws LAPStoreException {
		boolean isDone=false;
		if(lapcode!=null && isValidlapcode(lapcode)){
			lapDao.delete(lapcode);
			isDone = true;
		}else{
			throw new LAPStoreException("lapcode should be a capital letter followed by 3 digits");
		}
		return isDone;
	}

	@Override
	public Lap get(String lapcode) throws LAPStoreException {
		return lapDao.get(lapcode);
	}

	@Override
	public List<Lap> getAll() throws LAPStoreException {
		return lapDao.getAll();
	}

	
	

	



}
