package com.loanapp;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Map.Entry;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

import javax.sql.DataSource;

import org.apache.camel.Exchange;


public class Processing {
	
	private DataSource dataSource;
	   
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	

	private RemoteCacheManager cacheManager;
    private RemoteCache<Long, Object> studentCache;
    private Long rrollKey;
	 
    ConfigurationBuilder builder = new ConfigurationBuilder();
	
	public void getIFSCFromCache(Exchange exchange) throws SQLException{
		System.out.println("inside java method");
		rrollKey=exchange.getIn().getHeader("key",Long.class);
		String server=exchange.getIn().getHeader("hotrod_conn",String.class);
		String user=exchange.getIn().getHeader("hotrod_user",String.class);
		String pass=exchange.getIn().getHeader("hotrod_pass",String.class);
	builder.marshaller(new JavaSerializationMarshaller()).addJavaSerialWhiteList("java.util.", "java.util.HashMap")
				.addServers(server)
				.security().authentication().username(user)
				.password(pass);

		cacheManager = new RemoteCacheManager(builder.build());
	        studentCache = cacheManager.getCache("Student");
	        if(!studentCache.containsKey(rrollKey)){		
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement getStudentDetails = null;

                connection = dataSource.getConnection();
				getStudentDetails = connection.prepareStatement("select * from results_12 where rroll=?");
				getStudentDetails.setLong(1, rrollKey);
		        rs = getStudentDetails.executeQuery();
                System.out.println("after DB query is executed");
				while (rs.next()) {
                    System.out.println("from DB"+rs.getString("rroll"));
                    Map<String,String>studentlist = new HashMap<>();
					studentlist.put("reg",rs.getString("reg"));
					studentlist.put("sch",rs.getString("sch"));
					studentlist.put("rroll",rs.getString("rroll"));
					studentlist.put("cat",rs.getString("cat"));
					studentlist.put("cname",rs.getString("cname"));
					studentlist.put("mname",rs.getString("mname"));
					studentlist.put("fname",rs.getString("fname"));
					studentlist.put("dob",rs.getString("dob"));
					studentlist.put("sex",rs.getString("sex"));
					studentlist.put("scst",rs.getString("scst"));
					studentlist.put("hand",rs.getString("hand"));
					studentlist.put("sub1",rs.getString("sub1"));
					studentlist.put("sname1",rs.getString("sname1"));
					studentlist.put("mrk11",rs.getString("mrk11"));
					studentlist.put("mrk12",rs.getString("mrk12"));
					studentlist.put("mrk13",rs.getString("mrk13"));
					studentlist.put("pf1",rs.getString("pf1"));
					studentlist.put("gr1",rs.getString("gr1"));
					studentlist.put("sub2",rs.getString("sub2"));
					studentlist.put("sname2",rs.getString("sname2"));
					studentlist.put("mrk21",rs.getString("mrk21"));
					studentlist.put("mrk22",rs.getString("mrk22"));
					studentlist.put("mrk23",rs.getString("mrk23"));
					studentlist.put("pf2",rs.getString("pf2"));
					studentlist.put("gr2",rs.getString("gr2"));
					studentlist.put("sub3",rs.getString("sub3"));
					studentlist.put("sname3",rs.getString("sname3"));
					studentlist.put("mrk31",rs.getString("mrk31"));
					studentlist.put("mrk32",rs.getString("mrk32"));
					studentlist.put("mrk33",rs.getString("mrk33"));
					studentlist.put("pf3",rs.getString("pf3"));
					studentlist.put("gr3",rs.getString("gr3"));
					studentlist.put("sub4",rs.getString("sub4"));
					studentlist.put("sname4",rs.getString("sname4"));
					studentlist.put("mrk41",rs.getString("mrk41"));
					studentlist.put("mrk42",rs.getString("mrk42"));
					studentlist.put("mrk43",rs.getString("mrk43"));
					studentlist.put("pf4",rs.getString("pf4"));
					studentlist.put("gr4",rs.getString("gr4"));
					studentlist.put("sub5",rs.getString("sub5"));
					studentlist.put("sname5",rs.getString("sname5"));
					studentlist.put("mrk51",rs.getString("mrk51"));
					studentlist.put("mrk52",rs.getString("mrk52"));
					studentlist.put("mrk53",rs.getString("mrk53"));
					studentlist.put("pf5",rs.getString("pf5"));
					studentlist.put("gr5",rs.getString("gr5"));
					studentlist.put("sub6",rs.getString("sub6"));
					studentlist.put("sname6",rs.getString("sname6"));
					studentlist.put("mrk61",rs.getString("mrk61"));
					studentlist.put("mrk62",rs.getString("mrk62"));
					studentlist.put("mrk63",rs.getString("mrk63"));
					studentlist.put("pf6",rs.getString("pf6"));
					studentlist.put("gr6",rs.getString("gr6"));
					studentlist.put("isub1",rs.getString("isub1"));
					studentlist.put("isname1",rs.getString("isname1"));
					studentlist.put("igr1",rs.getString("igr1"));
					studentlist.put("isub2",rs.getString("isub2"));
					studentlist.put("isname2",rs.getString("isname2"));
					studentlist.put("igr2",rs.getString("igr2"));
					studentlist.put("isub3",rs.getString("isub3"));
					studentlist.put("isname3",rs.getString("isname3"));
					studentlist.put("igr3",rs.getString("igr3"));
					studentlist.put("tmrk",rs.getString("tmrk"));
					studentlist.put("comptt",rs.getString("comptt"));
					studentlist.put("res",rs.getString("res"));
					studentlist.put("rlrw",rs.getString("rlrw"));
					studentlist.put("date_rev",rs.getString("date_rev"));
					studentlist.put("stream",rs.getString("stream"));
					studentlist.put("abbr_name",rs.getString("abbr_name"));
					studentlist.put("state",rs.getString("state"));
					studentlist.put("cent",rs.getString("cent"));
					studentlist.put("schtype",rs.getString("schtype"));
					studentlist.put("admid",rs.getString("admid"));
					studentlist.put("month",rs.getString("month"));
					studentlist.put("dateofdecl",rs.getString("dateofdecl"));
					studentlist.put("session",rs.getString("session"));
					studentlist.put("skill",rs.getString("skill"));
					studentlist.put("nse",rs.getString("nse"));
					studentlist.put("nchmct",rs.getString("nchmct"));
					studentlist.put("mobile",rs.getString("mobile"));
                    rrollKey=rs.getLong("rroll");
                    studentCache.put(rrollKey, studentlist);
	            }
				rs.close();
				connection.close();
				getStudentDetails.close();
				Map<String,String> studentDetails =  (Map<String, String>) studentCache.get(rrollKey);
				exchange.getIn().setBody(studentDetails);
			}
			else{
			@SuppressWarnings("unchecked")
			Map<String,String> studentDetails =  (Map<String, String>) studentCache.get(rrollKey);
			System.out.println("Bank Details from cache :"+studentDetails);
			exchange.getIn().setBody(studentDetails);
			}
	}
	

}
