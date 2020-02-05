package com.cts.bscp.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cts.bscp.exception.LAPStoreException;
import com.cts.bscp.model.Lap;

public abstract class LapDAOJDBCImpl<ConnectionProvider> implements ILapDAO {
	
	ConnectionProvider conProvider;
	@Override
	public String add(Lap Lap) throws LAPStoreException {
		String Lapcode = null;
		if (Lap != null) {
			try (Connection con = ((Statement) conProvider).getConnection();
					PreparedStatement pInsert = con
							.prepareStatement(IQueryMapper.ADD_LAP_QRY)) {

				pInsert.setString(1, Lap.getlapcode());
				pInsert.setString(2, Lap.getCompanytitle());
				pInsert.setDouble(3, Lap.getPrice());
				pInsert.setDate(4, Date.valueOf(Lap.getReleaseDate()));

				int rowCount = pInsert.executeUpdate();

				if (rowCount == 1) {
					Lapcode = Lap.getlapcode();
				}
			} catch (SQLException exp) {
			
				throw new LAPStoreException("Lap is not inserted");
			}
		}
		return Lapcode;
	}

	@Override
	public boolean delete(String Lapcode) throws LAPStoreException {
		boolean isDone = false;

		try (Connection con = ((Statement) conProvider).getConnection();
				PreparedStatement pDelete = con
						.prepareStatement(IQueryMapper.DEL_LAP_QRY)) {

			pDelete.setString(1, Lapcode);

			int rowCount = pDelete.executeUpdate();

			if (rowCount == 1) {
				isDone = true;
			}
		} catch (SQLException exp) {
			
			throw new LAPStoreException("Lap is not inserted");
		}

		return isDone;
	}

	@Override
	public Lap get(String Lapcode) throws LAPStoreException {
		Lap Lap=null;
		try (Connection con = ((Statement) conProvider).getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_LAP_QRY)) {

			pSelect.setString(1, Lapcode);

			ResultSet rs = pSelect.executeQuery();
			
			if(rs.next()){
				Lap = new Lap();
				Lap.setlapcode(rs.getString("Lapcode"));
				Lap.setCompanytitle(rs.getString("title"));
				Lap.setPrice(rs.getDouble("price"));
				Lap.setReleaseDate(rs.getDate("pdate").toLocalDate());
			}
			
		} catch (SQLException exp) {
			
			throw new LAPStoreException("Lap is not available");
		}		
		return Lap;
	}

	@Override
	public java.util.List<Lap> getAll() throws LAPStoreException {
		ArrayList<Lap> Laps=null;
		try (Connection con =getConnection();
				PreparedStatement pSelect = con
						.prepareStatement(IQueryMapper.GET_ALL_LAPS_QRY)) {

			ResultSet rs = pSelect.executeQuery();
			
			Laps = new ArrayList<Lap>();
			
			while(rs.next()){
				Lap Lap = new Lap();
				Lap.setlapcode(rs.getString("Lapcode"));
				Lap.setCompanytitle(rs.getString("companytitle"));
				Lap.setPrice(rs.getDouble("price"));
				Lap.setReleaseDate(rs.getDate("rdate").toLocalDate());
				Laps.add(Lap);
			}
			
		} catch (SQLException exp) {
			
			throw new LAPStoreException("No Laps are available.");
		}		
		return Laps;	
	}

	protected abstract Connection getConnection();

	@Override
	public boolean update(Lap Lap) throws Exception {
		boolean isDone = false;
		if (Lap != null) {
			try (Connection con = ((Statement) conProvider).getConnection();
					PreparedStatement pUpdate = con
							.prepareStatement(IQueryMapper.MODIFY_LAP_QRY)) {

				
				pUpdate.setString(1, Lap.getCompanytitle());
				pUpdate.setDouble(2, Lap.getPrice());
				pUpdate.setDate(3, Date.valueOf(Lap.getReleaseDate()));
				pUpdate.setString(4, Lap.getlapcode());
				

				int rowCount = pUpdate.executeUpdate();

				if (rowCount == 1) {
					isDone = true;
				}
			} catch (SQLException exp) {
			
				throw new Exception("Lap is not updated.");
			}
		}
		return isDone;
	}

	
	}

	


