package com.flf.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Accident {

	private String id;
	private Date time;
	private String province;
	private String city;
	private String category_big;
	private String category_small;
	private String material;
	private String reason_type;
	private String result_type;
	private String accident_des;
	private String accident_reason;
	private String accident_reason_des;
	private String casualties;
	private String outages;
	private String other_results;
	private String loss_des;
	private String information_sources;
	private String image_url;
	private String note;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date accidentStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date accidentEnd;
	private Page page;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCategory_big() {
		return category_big;
	}
	public void setCategory_big(String category_big) {
		this.category_big = category_big;
	}
	public String getCategory_small() {
		return category_small;
	}
	public void setCategory_small(String category_small) {
		this.category_small = category_small;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getReason_type() {
		return reason_type;
	}
	public void setReason_type(String reason_type) {
		this.reason_type = reason_type;
	}
	public String getResult_type() {
		return result_type;
	}
	public void setResult_type(String result_type) {
		this.result_type = result_type;
	}
	public String getAccident_des() {
		return accident_des;
	}
	public void setAccident_des(String accident_des) {
		this.accident_des = accident_des;
	}
	public String getAccident_reason() {
		return accident_reason;
	}
	public void setAccident_reason(String accident_reason) {
		this.accident_reason = accident_reason;
	}
	public String getAccident_reason_des() {
		return accident_reason_des;
	}
	public void setAccident_reason_des(String accident_reason_des) {
		this.accident_reason_des = accident_reason_des;
	}
	public String getCasualties() {
		return casualties;
	}
	public void setCasualties(String casualties) {
		this.casualties = casualties;
	}
	public String getOutages() {
		return outages;
	}
	public void setOutages(String outages) {
		this.outages = outages;
	}
	public String getOther_results() {
		return other_results;
	}
	public void setOther_results(String other_results) {
		this.other_results = other_results;
	}
	public String getLoss_des() {
		return loss_des;
	}
	public void setLoss_des(String loss_des) {
		this.loss_des = loss_des;
	}
	public String getInformation_sources() {
		return information_sources;
	}
	public void setInformation_sources(String information_sources) {
		this.information_sources = information_sources;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getAccidentStart() {
		return accidentStart;
	}
	public void setAccidentStart(Date accidentStart) {
		this.accidentStart = accidentStart;
	}
	public Date getAccidentEnd() {
		return accidentEnd;
	}
	public void setAccidentEnd(Date accidentEnd) {
		this.accidentEnd = accidentEnd;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
	
	

}
