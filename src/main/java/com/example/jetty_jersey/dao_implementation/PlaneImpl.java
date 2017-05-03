
package com.example.jetty_jersey.dao_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.jetty_jersey.dao.MRO;
import com.example.jetty_jersey.dao.Plane;
import com.example.jetty_jersey.dao.Status;
import com.example.jetty_jersey.dao_interface.PlaneDao;
import com.example.jetty_jersey.db.DatabaseConnecter;
import com.example.jetty_jersey.db.Utility;

public class PlaneImpl implements PlaneDao
{
	public List<Plane> getPlanesbyType(String type)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("plane", "planetype", type);
		dbc.close();
		List<Plane> pl = new ArrayList<Plane>();
		for (Map<String, String> m : res)
		{
			Plane p = new Plane(Utility.convertIntString(m.get("planeId")), m.get("planetype"));
			pl.add(p);
		}
		return pl;
	}

	public List<Plane> getAllPlanes()
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableName("plane");
		dbc.close();
		List<Plane> pl = new ArrayList<Plane>();
		for (Map<String, String> m : res)
		{
			Plane p = new Plane(Utility.convertIntString(m.get("planeId")), m.get("planetype"));
			pl.add(p);
		}
		return pl;
	}

	public Plane getPlanebyId(int id)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("plane", "_id", Integer.toString(id));
		dbc.close();
		Map<String, String> ligne = res.get(0);
		Plane p = new Plane(Utility.convertIntString(ligne.get("_id")), ligne.get("planetype"));
		return p;
	}

	
	public Status addPlane(Plane plane) {
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status s = dbc.insertToTableName("plane", plane.toMap());
		dbc.close();
		return s;
}
	
	public Status deletePlane(int id)
	{
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status s = dbc.deleteAllFromTableNameWhereFieldEqValue("plane", "_id", String.valueOf(id));
		dbc.close();
		return s;
}
	//TEST
	/*public static void main(String[] args)
	{
		PlaneImpl pi=new PlaneImpl();
		Plane planetest=new Plane(-1, "Kaba en test");
		pi.getAllPlanes();
		//Status s = test.addMro(mro);
		pi.getPlanebyId(-1);
		System.out.println("au revoir");
	}*/

}
