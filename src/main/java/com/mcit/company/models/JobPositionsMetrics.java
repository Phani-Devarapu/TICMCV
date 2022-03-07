package com.mcit.company.models;

public class JobPositionsMetrics {

	private int totalPositons;
	private Long fulfilledPositons;
	private Long openPositoins;
	private Long totalPositionsByEnterprise;
	private Long openPositionsInEnterprise;
	
	
	public JobPositionsMetrics(int totalPositons, Long fulfilledPositons, Long openPositoins,
			Long totalPositionsByEnterprise, Long openPositionsInEnterprise) {
		super();
		this.totalPositons = totalPositons;
		this.fulfilledPositons = fulfilledPositons;
		this.openPositoins = openPositoins;
		this.totalPositionsByEnterprise = totalPositionsByEnterprise;
		this.openPositionsInEnterprise = openPositionsInEnterprise;
	}
	public JobPositionsMetrics() {
		super();
	}
	public int getTotalPositons() {
		return totalPositons;
	}
	public void setTotalPositons(int totalPositons) {
		this.totalPositons = totalPositons;
	}
	public Long getFulfilledPositons() {
		return fulfilledPositons;
	}
	public void setFulfilledPositons(Long fulfilledPositons) {
		this.fulfilledPositons = fulfilledPositons;
	}
	public Long getOpenPositoins() {
		return openPositoins;
	}
	public void setOpenPositoins(Long openPositoins) {
		this.openPositoins = openPositoins;
	}
	public Long getTotalPositionsByEnterprise() {
		return totalPositionsByEnterprise;
	}
	public void setTotalPositionsByEnterprise(Long totalPositionsByEnterprise) {
		this.totalPositionsByEnterprise = totalPositionsByEnterprise;
	}
	public Long getOpenPositionsInEnterprise() {
		return openPositionsInEnterprise;
	}
	public void setOpenPositionsInEnterprise(Long openPositionsInEnterprise) {
		this.openPositionsInEnterprise = openPositionsInEnterprise;
	}
	
	

}
