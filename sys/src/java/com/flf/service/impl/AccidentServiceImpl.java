package com.flf.service.impl;

import java.util.List;

import com.flf.entity.Accident;
import com.flf.entity.Page;
import com.flf.entity.Yyqj;
import com.flf.mapper.AccidentMapper;
import com.flf.service.AccidentService;

public class AccidentServiceImpl implements AccidentService{

	
	private AccidentMapper accidentMapper;
	
	public Accident getAccidentById(String accidentId) {
		// TODO Auto-generated method stub
		return accidentMapper.getAccidentById(accidentId);
	}

	public int insertAccident(Accident accident) {
		Accident acc = accidentMapper.getAccidentById(accident.getId());
		if(acc==null)
		  return 1;
		else{
			accidentMapper.insertAccident(accident);
			return 0;
		}
	}
	
	public int insertBatchAccident(List<Accident> list){
		return accidentMapper.insertBatchAccident(list);
	}

	public void updateAccident(Accident accident) {
		accidentMapper.updateAccident(accident);
	}

	public void updateAccidentBaseInfo(Accident accident) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAccident(int accidentId) {
		accidentMapper.deleteAccident(accidentId);
	}

	public List<Accident> listPageAccident(Accident accident) {
		return accidentMapper.listPageAccident(accident);
	}

	public AccidentMapper getAccidentMapper() {
		return accidentMapper;
	}

	public void setAccidentMapper(AccidentMapper accidentMapper) {
		this.accidentMapper = accidentMapper;
	}

	@Override
	public List<Accident> listPagePage(Page page) {
		return accidentMapper.listPagePage(page);
	}

	@Override
	public List<Accident> listAllAccident() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCategory() {
		return accidentMapper.getCategory();
	}

	@Override
	public List<Yyqj> getSizeByCategory(String category) {
		return accidentMapper.getSizeByCategory(category);
	}
	
	

}
