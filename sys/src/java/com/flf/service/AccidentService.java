package com.flf.service;

import java.util.List;

import com.flf.entity.Accident;
import com.flf.entity.Page;
import com.flf.entity.Yyqj;


public interface AccidentService {

    Accident getAccidentById(String accidentId);
	
	int insertAccident(Accident accident);
	
	int insertBatchAccident(List<Accident> list);
	
	void updateAccident(Accident accident);
	
	void deleteAccident(int accidentId);
	
	List<Accident> listPageAccident(Accident accident);
	
	List<Accident> listPagePage(Page page);
	
	List<Accident> listAllAccident();
	
	List<String> getCategory();
	
	List<Yyqj> getSizeByCategory(String category);
}
