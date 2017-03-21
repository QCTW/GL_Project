package com.example.jetty_jersey.db;

import java.io.IOException;
import java.util.Collections;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class DatabaseConnecter
{
	private final RestClient restClient = RestClient
			.builder(new HttpHost(DatabaseSettings.DB_HOST, DatabaseSettings.DB_PORT_DEFAULT, "http"), new HttpHost(DatabaseSettings.DB_HOST, DatabaseSettings.DB_PORT_SECOND, "http")).build();

	public Response execute(DatabaseQuery q)
	{
		Response r = null;
		try
		{
			r = restClient.performRequest(q.getMethod(), q.getURL(), Collections.<String, String> emptyMap(), q.getEntity());

		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public Response selectAllFromTableName(String tableName)
	{
		DatabaseQuery query = new DatabaseQuery(tableName, DatabaseExecuteType.SELECT);
		return execute(query);
	}

	public void close()
	{
		if (restClient != null)
			try
			{
				restClient.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
	}

	public Response selectAllFromTableWhereFieldEqValue(String tableName, String fieldName, String fieldValue)
	{
		DatabaseQuery query = new DatabaseQuery(tableName, DatabaseExecuteType.SELECT);
		query.addConditionEq(fieldName, fieldValue);
		return execute(query);
	}
}
