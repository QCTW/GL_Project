
package com.example.jetty_jersey.DaoInterfaceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.Dao.Status;
import com.example.jetty_jersey.DaoInterface.PlaneDao;
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

	public Status addPlane(Plane p)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
