public interface TaskTypeDao{
	
	List<TaskType> getTaskTypesbyHangerNeed(String hangarNeed);
	List<TaskType> getTaskTypesbyAtaCategory(String ataCategory);
	List<TaskType> getTaskTypesbyPeriodicityEquals(int period);
	List<TaskType> getTaskTypesbyPeriodicityLessThan(int period);
	List<TaskType> getTaskTypesbyPeriodicityGreaterThan(int period);
}