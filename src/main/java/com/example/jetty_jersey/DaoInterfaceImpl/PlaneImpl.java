
package com.example.jetty_jersey.DaoInterfaceImpl;


/*import java.util.ArrayList;
import java.util.HashMap;*/
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jetty_jersey.util.TaskInfo;
import com.example.jetty_jersey.Dao.Flight;
import com.example.jetty_jersey.Dao.MRO;
import com.example.jetty_jersey.Dao.Plane;
import com.example.jetty_jersey.Dao.Status;
import com.example.jetty_jersey.Dao.Status.Execution;
import com.example.jetty_jersey.Dao.Task;
import com.example.jetty_jersey.DaoInterface.PlaneDao;
import com.example.jetty_jersey.DaoInterface.TaskDao;
import com.example.jetty_jersey.db.DatabaseConnecter;
import com.example.jetty_jersey.db.DatabaseSettings;
import com.example.jetty_jersey.db.Utility;



public class PlaneImpl implements PlaneDao {

	public List<Plane> getPlanesbyType(String type) {
		
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("plane", "planeType", type);
		List<Plane> pl = new ArrayList<Plane>();
		for (Map<String, String> m : res)
		{
			Plane p = new Plane(Utility.convertIntString(ligne.get("planeId")), ligne.get("planeType"));
			
			pl.add(p);
			
		}
		dbc.close();
return pl;		

		// TODO Auto-generated method stub
		//return null;
	}

	public Plane getPlanebyId(int id) {
		DatabaseConnecter dbc = new DatabaseConnecter();
		List<Map<String, String>> res = dbc.selectAllFromTableWhereFieldEqValue("plane", "planeId", Integer.toString(id));
		Map<String, String> ligne=res.get(0);
		Plane p = new Plane(Utility.convertIntString(ligne.get("_id")), ligne.get("planeType"));
			

		dbc.close();
		return p;	
		// TODO Auto-generated method stub
		//return new Plane(id, "airbus");
	}

}
