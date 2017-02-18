public interface TaskDao{
	
	List<Task> getTaskbyDay(String dayMonthYear);
	List<Task> getTaskbyMonth(String monthYear);
	List<Task> getTaskbyHour(String hourDaymonth);
	List<Task> getTaskbyYear(String year);
	List<Task> getTaskbyStartDay(String dayMonthYear);
	List<Task> getTaskbyStartMonth(String monthYear);
	List<Task> getTaskbyStartHour(String hourDaymonth);
	List<Task> getTaskbyStartYear(String year);
	List<Task> getTaskbyEndDay(String dayMonthYear);
	List<Task> getTaskbyEndMonth(String monthYear);
	List<Task> getTaskbyEndHour(String hourDaymonth);
	List<Task> getTaskbyEndYear(String year);


}