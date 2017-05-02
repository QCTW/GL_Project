/** PUT gla/taskList/1
 *{
 *
 * "periodicity" : "tous les six mois",
 * "ataCategory" : "ata2",
 * "hangarNeed" : "true"
 *
 *}
 **/
package com.example.jetty_jersey.dao;

public class TaskList{
	
	private String periodicity;
	private String ataCategory;
	private Boolean hangarNeed;
	
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}
	public String getAtaCategory() {
		return ataCategory;
	}
	public void setAtaCategory(String ataCategory) {
		this.ataCategory = ataCategory;
	}
	public Boolean getHangarNeed() {
		return hangarNeed;
	}
	public void setHangarNeed(Boolean hangarNeed) {
		this.hangarNeed = hangarNeed;
	}
	

	

}
