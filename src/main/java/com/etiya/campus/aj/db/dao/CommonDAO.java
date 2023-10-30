package com.etiya.campus.aj.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommonDAO extends BaseDAO{
    public Map<String,String> getBsnInterStShrtCodes(Connection connection){
        String query = "SELECT BSN_INTER_ST_ID, SHRT_CODE FROM PRFX.BSN_INTER_ST WHERE IS_ACTV=1";
        return this.executeQuery(connection, this.applyCurrentPrefix(query));
    }
    public Map<String,String> getGnlStShrtCodes(Connection connection){
        String query = "SELECT GNL_ST_ID, SHRT_CODE FROM PRFX.GNL_ST WHERE IS_ACTV=1";
        return this.executeQuery(connection, this.applyCurrentPrefix(query));
    }
    public Map<String,String> getGnlTpShrtCodes(Connection connection){
        String query = "SELECT GNL_TP_ID, SHRT_CODE FROM PRFX.GNL_TP WHERE IS_ACTV=1";
        return this.executeQuery(connection, this.applyCurrentPrefix(query));
    }
    public Map<String,String> getOrdItemActnTpShrtCodes(Connection connection){
        String query = "SELECT ORD_ITEM_ACTN_TP_ID, SHRT_CODE FROM PRFX.ORD_ITEM_ACTN_TP WHERE IS_ACTV=1";
        return this.executeQuery(connection, this.applyCurrentPrefix(query));
    }
    public Map<String,String> getOrdItemStShrtCodes(Connection connection){
        String query = "SELECT ORD_ITEM_ST_ID, SHRT_CODE FROM PRFX.ORD_ITEM_ST WHERE IS_ACTV=1";
        return this.executeQuery(connection, this.applyCurrentPrefix(query));
    }
    public Map<String,String> getOrdStShrtCodes(Connection connection){
        String query = "SELECT ORD_ST_ID, SHRT_CODE FROM PRFX.ORD_ST WHERE IS_ACTV=1";
        return this.executeQuery(connection, this.applyCurrentPrefix(query));
    }

    public List<Map<String,String>> getJobIncsList(Connection connection){
        String query = "SELECT * FROM SCH_JOB_INSC WHERE IS_ACTV = 1 ORDER BY DESCR ASC";
        return this.executeQueryForListResult(connection, this.applyCurrentPrefix(query));
    }

    public List<Map<String,String>> getJobRunLog(Connection connection, Long schJobInscId, Date startDate, Date endDate){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(schJobInscId!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
            String query = "SELECT * FROM SCH_JOB_RUN_LOG  WHERE SCH_JOB_INSC_ID = ? "
                    + "AND CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss') AND IS_ACTV = 1 ORDER BY CDATE DESC";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setLong(ndx++, schJobInscId);
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
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

    public Integer getCampaignJobProcessCount(Connection connection, Long schJobInscId, Date startDate, Date endDate){
        Integer result = new Integer(0);
        if((connection!=null)&&(schJobInscId!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
            String query =         "SELECT COUNT(GPD.GNL_PROC_DATA_ID) CNT FROM GNL_PROC_DATA GPD, CMPG_PKG_CHNG_JOB_DETAIL DT \n" +
                    "WHERE GPD.REF_ID = DT.CMPG_PKG_CHNG_JOB_DETAIL_ID \n" +
                    "AND GPD.CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss') \n" +
                    "AND GPD.PROC_ID = ?";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
                stmt.setLong(ndx++, schJobInscId);
                List<Map<String,String>> rows = this.executeStatement(stmt);
                if((rows!= null)&&(rows.size()>0)){
                    result = new Integer(rows.get(0).get("CNT"));
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

    public List<Map<String,String>> getCampaignJobProcess(Connection connection, Long schJobInscId, Date startDate, Date endDate, Integer firstRow, Integer lastRow){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(schJobInscId!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
            String query =         "SELECT\n" +
                    "RNUM, \n" +
                    "CMPG_ID1, \n" +
                    "CMPG_ID2, \n" +
                    "PROD_ID1,\n" +
                    "PROD_ID2, \n" +
                    "CNCL_ORD_ID, \n" +
                    "CNCL_ORD_HSTR, \n" +
                    "SALE_ORD_ID, \n" +
                    "SALE_ORD_HSTR, \n" +
                    "SALE_RETRY_COUNT, \n" +
                    "GPD_CDATE, \n" +
                    "GPD_UDATE,\n" +
                    "GNL_PROC_DATA_ID, \n" +
                    "GPD_ST_ID,\n" +
                    "REL_TP_ID,\n" +
                    "IS_ACTV, \n" +
                    "DT_CDATE,\n" +
                    "DT_UDATE\n" +
                    "FROM \n" +
                    "(SELECT \n" +
                    "GPD.CDATE AS GPD_CDATE, \n" +
                    "GPD.UDATE AS GPD_UDATE,\n" +
                    "GPD.GNL_PROC_DATA_ID, \n" +
                    "GPD.ST_ID AS GPD_ST_ID,\n" +
                    "DT.CMPG_ID1, \n" +
                    "DT.CMPG_ID2, \n" +
                    "DT.PROD_ID1,\n" +
                    "DT.PROD_ID2, \n" +
                    "DT.REL_TP_ID,\n" +
                    "DT.CNCL_ORD_ID, \n" +
                    "DT.CNCL_ORD_HSTR, \n" +
                    "DT.SALE_ORD_ID, \n" +
                    "DT.SALE_ORD_HSTR, \n" +
                    "DT.SALE_RETRY_COUNT, \n" +
                    "DT.IS_ACTV, \n" +
                    "DT.CDATE AS DT_CDATE,\n" +
                    "DT.UDATE AS DT_UDATE,\n" +
                    "ROWNUM AS RNUM \n" +
                    "FROM GNL_PROC_DATA GPD, CMPG_PKG_CHNG_JOB_DETAIL DT \n" +
                    "WHERE GPD.REF_ID = DT.CMPG_PKG_CHNG_JOB_DETAIL_ID \n" +
                    "AND GPD.PROC_ID = ?\n" +
                    "AND GPD.CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss') \n" +
                    "AND ROWNUM <= ?\n" +
                    ")\n" +
                    "WHERE   RNUM >= ?";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setLong(ndx++, schJobInscId);
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
                stmt.setInt(ndx++, lastRow);
                stmt.setInt(ndx++, firstRow);
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

    public Integer getTemproraryJobProcessCount(Connection connection, Long schJobInscId, Date startDate, Date endDate){
        Integer result = new Integer(0);
        if((connection!=null)&&(schJobInscId!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
            String query = "SELECT COUNT(GPD.GNL_PROC_DATA_ID) CNT FROM GNL_PROC_DATA GPD \n" +
                    "WHERE GPD.CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss') \n" +
                    "AND GPD.PROC_ID = ?";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
                stmt.setLong(ndx++, schJobInscId);
                List<Map<String,String>> rows = this.executeStatement(stmt);
                if((rows!= null)&&(rows.size()>0)){
                    result = new Integer(rows.get(0).get("CNT"));
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

    public List<Map<String,String>> getTemproraryJobProcess(Connection connection, Long schJobInscId, Date startDate, Date endDate, Integer firstRow, Integer lastRow){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(schJobInscId!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
            String query =         "SELECT\n" +
                    "REF_ID, \n" +
                    "CDATE, \n" +
                    "UDATE,\n" +
                    "GNL_PROC_DATA_ID, \n" +
                    "ST_ID,\n" +
                    "RNUM \n" +
                    "FROM \n" +
                    "(SELECT \n" +
                    "GPD.REF_ID, \n" +
                    "GPD.CDATE, \n" +
                    "GPD.UDATE,\n" +
                    "GPD.GNL_PROC_DATA_ID, \n" +
                    "GPD.ST_ID,\n" +
                    "ROWNUM AS RNUM \n" +
                    "FROM GNL_PROC_DATA GPD \n" +
                    "WHERE GPD.PROC_ID = ?\n" +
                    "AND GPD.CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss') \n" +
                    "AND ROWNUM <= ?\n" +
                    ")\n" +
                    "WHERE   RNUM >= ?";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setLong(ndx++, schJobInscId);
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
                stmt.setInt(ndx++, lastRow);
                stmt.setInt(ndx++, firstRow);
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

    public List<Map<String,String>> searchProdForCampaignJob(Connection connection, Long prodId, Date startDate, Date endDate){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(prodId!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
            String query =
                    "SELECT \n" +
                            "GPD.CDATE AS GPD_CDATE, \n" +
                            "GPD.UDATE AS GPD_UDATE,\n" +
                            "GPD.GNL_PROC_DATA_ID, \n" +
                            "GPD.ST_ID AS GPD_ST_ID,\n" +
                            "DT.CMPG_ID1, \n" +
                            "DT.CMPG_ID2, \n" +
                            "DT.PROD_ID1,\n" +
                            "DT.PROD_ID2, \n" +
                            "DT.REL_TP_ID,\n" +
                            "DT.CNCL_ORD_ID, \n" +
                            "DT.CNCL_ORD_HSTR, \n" +
                            "DT.SALE_ORD_ID, \n" +
                            "DT.SALE_ORD_HSTR, \n" +
                            "DT.SALE_RETRY_COUNT, \n" +
                            "DT.IS_ACTV, \n" +
                            "DT.CDATE AS DT_CDATE,\n" +
                            "DT.UDATE AS DT_UDATE,\n" +
                            "ROWNUM AS RNUM \n" +
                            "FROM GNL_PROC_DATA GPD, CMPG_PKG_CHNG_JOB_DETAIL DT \n" +
                            "WHERE GPD.REF_ID = DT.CMPG_PKG_CHNG_JOB_DETAIL_ID \n" +
                            "AND (DT.PROD_ID1 = ? OR DT.PROD_ID2 = ?) \n" +
                            "AND GPD.CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss') \n";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setLong(ndx++, prodId);
                stmt.setLong(ndx++, prodId);
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
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

    public List<Map<String,String>> searchProdForTemproraryJob(Connection connection, Long prodId, Date startDate, Date endDate){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(prodId!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
            String query =
                    "SELECT \n" +
                            "GPD.REF_ID, \n" +
                            "GPD.CDATE, \n" +
                            "GPD.UDATE,\n" +
                            "GPD.GNL_PROC_DATA_ID, \n" +
                            "GPD.ST_ID\n" +
                            "FROM GNL_PROC_DATA GPD \n" +
                            "WHERE GPD.REF_ID = ?\n" +
                            "AND GPD.CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss') \n";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setLong(ndx++, prodId);
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
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

    public Map<String,String> getAddressByProductId(Connection connection, Long productId){
        String query = "SELECT * FROM ADDR\n" +
                "WHERE DATA_TP_ID = 20 AND ROW_ID=? ";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), productId);
    }

}
