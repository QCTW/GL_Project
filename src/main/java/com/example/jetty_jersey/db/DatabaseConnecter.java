package com.example.jetty_jersey.db;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.*;

public class DatabaseConnecter
{
	private static Logger log = LogManager.getLogger(DatabaseConnecter.class.getName());
	private final RestClient restClient = RestClient
			.builder(new HttpHost(DatabaseSettings.DB_HOST, DatabaseSettings.DB_PORT_DEFAULT, "http"), new HttpHost(DatabaseSettings.DB_HOST, DatabaseSettings.DB_PORT_SECOND, "http")).build();

	// About TransportClient : https://www.devdiscoveries.com/elasticsearch/java-elasticsearch-become-up-and-running/
	private final Settings settings = Settings.builder().put("client.transport.sniff", true).build();
	@SuppressWarnings("unchecked")
	private final TransportClient client = new PreBuiltTransportClient(settings);

	public DatabaseConnecter()
	{
		try
		{
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(DatabaseSettings.DB_HOST), DatabaseSettings.DB_PORT_TRANSPORT));
		} catch (UnknownHostException e)
		{
			log.error("Error while initializing database connection. \n"
					+ "Please check that cluster.name in /usr/local/etc/elasticsearch/elasticsearch.yml is properly configured as 'cluster.name: elasticsearch'.", e);
		}
	}

	public Response execute(DatabaseQuery q)
	{
		Response r = null;
		try
		{
			r = restClient.performRequest(q.getMethod(), q.getURL(), Collections.<String, String> emptyMap(), q.getEntity());

		} catch (IOException e)
		{
			log.error("Error while performing REST database request", e);
		} catch (Exception e)
		{
			log.error("Exception while performing REST database request", e);
		}
		return r;
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 */
	public List<Map<String, String>> selectAllFromTableName(String tableName)
	{
		SearchResponse scrollResp = client.prepareSearch(DatabaseSettings.DB_NAME).setTypes(tableName).addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
				.setScroll(DatabaseSettings.MAX_DATA_KEEP_TIME).setSize(DatabaseSettings.MAX_BOLK_RESULTS).get();
		// Scroll until no hits are returned
		List<Map<String, String>> lRet = new ArrayList<Map<String, String>>();
		do
		{
			lRet.addAll(wrapResults(scrollResp));
			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(DatabaseSettings.MAX_DATA_KEEP_TIME).execute().actionGet();
		} while (scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
		return lRet;
	}

	public List<Map<String, String>> selectAllFromTableWhereFieldEqValue(String tableName, String fieldName, String fieldValue)
	{
		QueryBuilder qb = QueryBuilders.termQuery(fieldName, fieldValue);
		SearchResponse scrollResp = client.prepareSearch(DatabaseSettings.DB_NAME).setTypes(tableName).addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
				.setScroll(DatabaseSettings.MAX_DATA_KEEP_TIME).setQuery(qb).setSize(DatabaseSettings.MAX_BOLK_RESULTS).get();
		// Scroll until no hits are returned
		List<Map<String, String>> lRet = new ArrayList<Map<String, String>>();
		do
		{
			lRet.addAll(wrapResults(scrollResp));
			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(DatabaseSettings.MAX_DATA_KEEP_TIME).execute().actionGet();
		} while (scrollResp.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
		return lRet;
	}

	private List<Map<String, String>> wrapResults(SearchResponse res)
	{
		List<Map<String, String>> lRet = new ArrayList<Map<String, String>>();
		for (SearchHit hit : res.getHits().getHits())
		{
			CustomHashMap<String, String> hm = new CustomHashMap<String, String>();
			lRet.add(hm);
			hm.put("_db", hit.getIndex());
			hm.put("id", hit.getId());
			for (Entry<String, Object> s : hit.getSource().entrySet())
			{
				hm.putIfAbsent(s.getKey(), (String) s.getValue());
			}
		}
		return lRet;
	}

	public List<Map<String, String>> selectInRangeFromTableName(String tableName, int nStart, int nEnd)
	{
		SearchResponse response = client.prepareSearch(DatabaseSettings.DB_NAME).setTypes(tableName).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setFrom(nStart).setSize(nEnd).setExplain(true)
				.get();
		log.debug("selectInRangeFromTableName(" + tableName + "," + nStart + "" + nEnd + ") result:\n" + response.toString());
		return wrapResults(response);
	}

	public void close()
	{
		if (restClient != null)
			try
			{
				restClient.close();
			} catch (IOException e)
			{
				log.error("Unable to close REST database connection", e);
			}

		if (client != null)
			client.close();
	}

}
