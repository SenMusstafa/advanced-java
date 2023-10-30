package com.etiya.campus.aj.db.dao;

import com.etiya.campus.aj.domain.common.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDAO {
    private String tablePrefix;

    protected List<Map<String,String>> executeStatement(PreparedStatement   stmt){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        Map<String,String> row = null;

        if(stmt!=null){
            ResultSet rs = null;
            try{
                rs=stmt.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                while(rs.next()){
                    row = new HashMap<String,String>();
                    for(int i=1; i<=columns; ++i){
                        row.put(md.getColumnName(i),rs.getString(i));
                    }
                    result.add(row);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(rs!=null)
                        rs.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
        }
        return result;
    }

    /*
     * String query parametresi ile gönderilen sorguyu calistirir
     * Sorgu key-value ikilileri donecek sekilde olmalidir
     */
    protected Map<String,String> executeQuery(Connection connection, String query){
        Map<String,String> result = new HashMap<String,String>();
        if((connection!=null)&&(query!=null)){
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try{
                stmt = connection.prepareStatement(query);
                rs=stmt.executeQuery();
                while(rs.next()){
                    result.put(rs.getString(1),rs.getString(2));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(stmt!=null)
                        rs.close();
                    stmt.close();
                }catch(SQLException se){

                }
            }
        }
        return result;
    }

    /*
     * Tek bir parametre ile calisan sorgular icin
     * Tek bir sonuc donecegi varsayilir
     * Resultseti Map olarak doner
     */
    protected Map<String,String> executeQueryWithSingleParameterForSingleResult(Connection connection, String query, Long id){
        Map<String,String> result = new HashMap<String,String>();
        if((connection!=null)&&(query!=null)&&(id!=null)){
            PreparedStatement stmt = null;
            try{
                stmt = connection.prepareStatement(query);
                stmt.setLong(1, id);
                List<Map<String,String>> rows = this.executeStatement(stmt);
                if((rows!= null)&&(rows.size()>0)){
                    result = rows.get(0);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se){

                }
            }
        }
        return result;
    }

    /*
     * Tek bir parametre ile calisan sorgular icin
     * Tek bir sonuc donecegi varsayilir
     * Resultseti Map olarak doner
     */
    protected Map<String,String> executeQueryWithSingleParameterForSingleResult(Connection connection, String query, String key){
        Map<String,String> result = new HashMap<String,String>();
        if((connection!=null)&&(query!=null)&&(key!=null)){
            PreparedStatement stmt = null;
            try{
                stmt = connection.prepareStatement(query);
                stmt.setString(1, key);
                List<Map<String,String>> rows = this.executeStatement(stmt);
                if((rows!= null)&&(rows.size()>0)){
                    result = rows.get(0);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se){

                }
            }
        }
        return result;
    }

    /*
     * Tek bir parametre ile calisan sorgular icin
     * Coklu sonuc donecegi varsayilir
     * Resultseti Map Listesi olarak doner
     */
    protected List<Map<String,String>> executeQueryWithSingleParameterForListResult(Connection connection, String query, Long id){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(query!=null)&&(id!=null)){
            PreparedStatement stmt = null;
            try{
                stmt = connection.prepareStatement(query);
                stmt.setLong(1, id);
                List<Map<String,String>> rows = this.executeStatement(stmt);
                if((rows!= null)&&(rows.size()>0)){
                    result = rows;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se){

                }
            }
        }
        return result;
    }

    protected List<Map<String,String>> executeQueryWithSingleParameterForListResult(Connection connection, String query, String id){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(query!=null)&&(id!=null)){
            PreparedStatement stmt = null;
            try{
                stmt = connection.prepareStatement(query);
                stmt.setString(1, id);
                List<Map<String,String>> rows = this.executeStatement(stmt);
                if((rows!= null)&&(rows.size()>0)){
                    result = rows;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se){

                }
            }
        }
        return result;
    }

    protected List<Map<String,String>> executeQueryForListResult(Connection connection, String query){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(query!=null)){
            PreparedStatement stmt = null;
            try{
                stmt = connection.prepareStatement(query);
                List<Map<String,String>> rows = this.executeStatement(stmt);
                if((rows!= null)&&(rows.size()>0)){
                    result = rows;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se){

                }
            }
        }
        return result;
    }

    protected String applyCurrentPrefix(String query){
        String result = query;
        if(query!=null){
            if(this.getTablePrefix() .equals(Constants.NO_PREFIX)){
                result = query.replaceAll(Constants.DUMMY_PREFIX+".", "");
            }
            else{
                result = query.replaceAll(Constants.DUMMY_PREFIX, this.getTablePrefix() );
            }
        }
        return result;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }
}
