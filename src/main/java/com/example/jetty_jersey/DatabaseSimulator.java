package com.example.jetty_jersey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class DatabaseSimulator
{

	public static void main(String[] args) throws Exception
	{
		String queryPath = "src/main/resources/initDB.query";
		File fQuery = new File(queryPath);
		System.out.println(fQuery.getAbsolutePath());
		FileInputStream fstream = new FileInputStream(queryPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		ArrayList<DatabaseQuery> queries = new ArrayList<DatabaseQuery>();
		String strLine;
		int syntaxStatus = 0;
		DatabaseQuery currentQuery = null;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null)
		{
			strLine = strLine.trim();
			if (strLine.startsWith("##") || strLine.length() == 0)
				continue;
			if (strLine.startsWith("PUT") || strLine.startsWith("GET") || strLine.startsWith("POST") || strLine.startsWith("DELETE"))
			{
				if (syntaxStatus != 0)
					throw new Exception("Incorrect syntax : Query is not closed with {...}");

				int pos1StSpace = strLine.indexOf(" ");
				String strMethod = strLine.substring(0, pos1StSpace);
				String strURL = strLine.substring(pos1StSpace + 1, strLine.length());
				currentQuery = new DatabaseQuery(strMethod.trim(), strURL.trim());
				syntaxStatus++;
			} else
			{
				if (strLine.length() > 0)
				{
					if (currentQuery != null && syntaxStatus > 0)
					{
						if (syntaxStatus == 1)
						{
							if (strLine.startsWith("{"))
							{
								syntaxStatus++;
								strLine = strLine.substring(1).trim();
							}
						}

						if (syntaxStatus == 2 && strLine.length() > 0)
						{
							if (strLine.startsWith("}"))
							{
								syntaxStatus = 0;
								queries.add(currentQuery);
							} else
							{
								processQuery(currentQuery, strLine);
								if (strLine.endsWith("}"))
								{
									syntaxStatus = 0;
									queries.add(currentQuery);
								}
							}
						}
					} else
					{
						throw new Exception("Incorrect syntax : Query should start with \"WebServiceMethod PATH/TO/DATABASE...\"");
					}
				}
			}
		}

		// Close the input stream
		br.close();
		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9201, "http")).build();
		for (DatabaseQuery q : queries)
		{
			Response indexResponse = restClient.performRequest(q.getMethod(), q.getURL(), Collections.<String, String> emptyMap(), q.getEntity());
			// StatusLine rl = indexResponse.getStatusLine();
			System.out.println(EntityUtils.toString(indexResponse.getEntity()));
		}
		restClient.close();
	}

	private static void processQuery(DatabaseQuery currentQuery, String strLine) throws Exception
	{
		if (strLine.endsWith("}"))
			strLine = strLine.substring(0, strLine.length() - 1);

		int pos1Comma = strLine.indexOf(":");
		if (pos1Comma == -1)
			throw new Exception("Incorrect key-value syntax : Missing comma between key and value");

		currentQuery.putData(strLine);
	}

}
