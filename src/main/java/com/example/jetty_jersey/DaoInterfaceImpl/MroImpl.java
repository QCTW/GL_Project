package com.example.jetty_jersey.DaoInterfaceImpl;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jetty_jersey.Dao.MCC;
import com.example.jetty_jersey.Dao.MRO;
import com.example.jetty_jersey.Dao.Status;
import com.example.jetty_jersey.DaoInterface.MroDao;
import com.example.jetty_jersey.db.DatabaseConnecter;
import com.example.jetty_jersey.db.DatabaseSettings;
import com.example.jetty_jersey.db.Utility;

public class MroImpl implements MroDao {

	private static Logger log = LogManager.getLogger(MccImpl.class.getName());
	private final Map<String, MRO> mroCache = new HashMap<String, MRO>();
	
	public List<MRO> getMrobyQualification(String qualification) {
		DatabaseConnecter dbc=new DatabaseConnecter();
		
		return null;
	}
	
	public List<MRO> getMrosInRange(int iStart, int iEnd)
	{
		DatabaseConnecter dbConnect = new DatabaseConnecter();
		List<Map<String, String>> results = dbConnect.selectInRangeFromTableName("Mro", iStart, iEnd);
		List<MRO> mroList = new ArrayList<MRO>();
		for (Map<String, String> m : results)
		{
			MRO mro = new MRO(Utility.convertIntString(m.get("_id")),m.get("name"));
			MRO wrap = mro;
			mroList.add(wrap);
			System.out.println(mro.toString());

		}
		dbConnect.close();
		return mroList;
	}

	public List<MRO> getAllMros() {
		return getMrosInRange(0, DatabaseSettings.MAX_RESULTS_PER_QUERY);
	}

	public Status addMro(MRO mro) {
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status s = dbc.insertToTableName("mro", mro.toMap());
		dbc.close();
		return s;
	}

	public Status modifyMro(MRO mro) {
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status s = dbc.updateDataInTableNameWhereFieldEqValue("mro", "_id", String.valueOf(mro.getId()), mro.toMap());
		dbc.close();
		return s;
	}

	public Status deleteMro(int id) {
		DatabaseConnecter dbc = new DatabaseConnecter();
		Status deletedStatus = dbc.deleteAllFromTableNameWhereFieldEqValue("mro", "_id", String.valueOf(id));
		dbc.close();
		return deletedStatus;
	}
	
	public static void main(String[] args)
	{
		MroImpl test = new MroImpl();
		MRO mro=new MRO(-1, "Kaba en test");
		test.getAllMros();
		Status s = test.addMro(mro);
		System.out.println("add Mro _id=1:" + s.toString());
	}

}
