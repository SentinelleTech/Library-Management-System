package com.kise.products;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class DatabaseToJSON {
	
	Connection con;
	Pool p;
	Statement st;
	ResultSet rs;

	public DatabaseToJSON(){
		
		p = new Pool("root", "", "Products_DB");
		
		con = p.createConnection();
		
		try {			
			
			st = con.createStatement();
			
			rs = st.executeQuery("select * from products");
			
			//int total_rows = rs.getMetaData().getColumnCount();
            //for (int i = 0; i < total_rows; i++) {
            	System.out.println(getJSONFromResultSet(rs
            			//rs.getMetaData().getColumnLabel(i + 1).toLowerCase())
            			));
            //}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	 /**
     * Convert a result set into a JSON Array
     * @param resultSet
     * @return a JSONArray
     * @throws Exception
     */
//    public static JSONArray convertToJSON(ResultSet rs)throws Exception {
//    	
//        JSONArray jsonArray = new JSONArray();
//        
//        while (rs.next()) {
//            int total_rows = rs.getMetaData().getColumnCount();
//            for (int i = 0; i < total_rows; i++) {
//            	
//                JSONObject obj = new JSONObject();
//                obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
//                
//                jsonArray.add(obj);
//                
//                //jsonArray.put(obj);
//            }
//        }
//        return jsonArray;
//    }
    
    public String getJSONFromResultSet(ResultSet rs) {
        Map json = new HashMap(); 
        List list = new ArrayList();
        if(rs!=null)
        {
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                while(rs.next())
                {
                    Map<String,Object> columnMap = new HashMap<String, Object>();
                    for(int columnIndex=1;columnIndex<=metaData.getColumnCount();columnIndex++)
                    {
                        if(rs.getString(metaData.getColumnName(columnIndex))!=null) {
                        	
                        	list.add(rs.getString(metaData.getColumnName(columnIndex)));
                        	
                           //columnMap.put(metaData.getColumnLabel(columnIndex),     rs.getString(metaData.getColumnName(columnIndex)));
                        }else {
                            columnMap.put(metaData.getColumnLabel(columnIndex), "");
                        }
                        
                    }
                    //list.add(columnMap);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            json.put("", list);
         }
         return JSONValue.toJSONString(json);
    }
    
    public static void main(String[] args) {
		new DatabaseToJSON();
	}

}
