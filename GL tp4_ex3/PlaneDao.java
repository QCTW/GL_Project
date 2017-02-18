public interface PlaneDao{
	
	List<Plane> getPlanesbyType(String type);
	Plane getPlanebyId(String id);
}