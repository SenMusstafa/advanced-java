package com.etiya.campus.aj.db.dao;

import com.etiya.campus.aj.util.IOUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CustomerOrderDAO extends BaseDAO {
    public Map<String,String> getCustomerOrderInfo(Connection connection, Long custOrdId){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.ORD_ST WHERE ORD_ST_ID = CO.ORD_ST_ID) AS STATUS,"
                + "(SELECT SHRT_CODE FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_SHRT_CODE, "
                + "(SELECT EXT_SHRT_CODE FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_EXT_SHRT_CODE, "
                + "(SELECT NAME FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_NAME, "
                + "CO.* FROM PRFX.CUST_ORD co WHERE CO.CUST_ORD_ID = ?";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), custOrdId);
    }

    public Map<String,String> getCustomerOrderByBsnInter(Connection connection, Long bsnInterId){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.ORD_ST WHERE ORD_ST_ID = CO.ORD_ST_ID) AS STATUS,"
                + "(SELECT SHRT_CODE FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_SHRT_CODE, "
                + "(SELECT EXT_SHRT_CODE FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_EXT_SHRT_CODE, "
                + "(SELECT NAME FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_NAME, "
                + "CO.* FROM PRFX.CUST_ORD co WHERE CO.BSN_INTER_ID = ?";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), bsnInterId);
    }

    public Map<String,String> getBsnInter(Connection connection, Long bsnInterId){
        String query = "SELECT * FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = ?";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), bsnInterId);
    }

    //getCustomerOrderStatusHistory

    public List<Map<String,String>> getCustomerOrderItems(Connection connection, Long custOrdId){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.ORD_ITEM_ST WHERE ORD_ITEM_ST_ID = COI.ORD_ITEM_ST_ID) AS STATUS,"
                +"(SELECT SHRT_CODE FROM PRFX.ORD_ITEM_ACTN_TP WHERE ORD_ITEM_ACTN_TP_ID = COI.ORD_ITEM_ACTN_TP_ID) AS ACTIONTYPE,"
                + "COI.* FROM PRFX.CUST_ORD_ITEM COI WHERE COI.CUST_ORD_ID = ?";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), custOrdId);
    }

    public List<Map<String,String>> getPostActions(Connection connection, Long bsnInterId){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(bsnInterId!=null)){
            PreparedStatement stmt = null;
            String query = "SELECT ACTN_NAME, RSLT_CODE, RSLT_MSG, TRNSC_ID, IS_ACTV, CDATE, UDATE, "
                    + "SDATE, EDATE, ACTN_RULE_ID, PAY_LOAD FROM PRFX.POST_ACTN_ENT WHERE BSN_INTER_ID = ?";
            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                stmt.setLong(1, bsnInterId);
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

    public List<Map<String,String>> getOrderStHstr(Connection connection, Long custOrdId){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.ORD_ST WHERE ORD_ST_ID = HSTR.ORD_ST_ID) AS STATUS_CODE,"
                + "(SELECT SHRT_CODE FROM PRFX.ORD_ST WHERE ORD_ST_ID = HSTR.PREV_ORD_ST_ID) AS PREV_STATUS_CODE,"
                + "HSTR.* FROM PRFX.CUST_ORD_ST_HSTR HSTR WHERE HSTR.CUST_ORD_ID = ? ORDER BY HSTR.CUST_ORD_ST_HSTR_ID";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), custOrdId);
    }

    public List<Map<String,String>> getOrderItemStHstr(Connection connection, Long custOrdId){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.ORD_ITEM_ST WHERE ORD_ITEM_ST_ID = HSTR.ORD_ITEM_ST_ID) AS STATUS_CODE,"
                + "(SELECT SHRT_CODE FROM PRFX.ORD_ITEM_ST WHERE ORD_ITEM_ST_ID = HSTR.PREV_ORD_ITEM_ST_ID) AS PREV_STATUS_CODE,"
                + "HSTR.* FROM PRFX.CUST_ORD_ITEM_ST_HSTR HSTR WHERE HSTR.CUST_ORD_ITEM_ID = ? ORDER BY HSTR.CUST_ORD_ITEM_ST_HSTR_ID";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), custOrdId);
    }

    public List<Map<String,String>> getServiceCalls(Connection connection, String custOrdId){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(custOrdId!=null)){
            PreparedStatement stmt = null;
			/*String query ="   SELECT i.GNL_EXT_SYST_CALL_LOG_ID,\n" +
					"          i.GNL_EXT_WEB_SRVC_OPER_ID,\n" +
					"          i.GNL_EXT_SYST_CALL_QUEUE_ID,\n" +
					"          i.TXN_ID,\n" +
					"          i.SRVC_NAME,\n" +
					"          i.OPER_NAME,\n" +
					"          i.DESCR,\n" +
					"          i.ERR_MSG,\n" +
					"          i.ERR_CODE,\n" +
					"          i.ERR_DATE,\n" +
					"          i.CLIENT_IP,\n" +
					"          i.SRVR_NAME,\n" +
					"          NVL (o.LOG_CORR_ID, i.LOG_CORR_ID) LOG_CORR_ID,\n" +
					"          O.GNL_EXT_SYST_CALL_LOG_ID child_GNL_EXT_SYST_CALL_LOG_ID,\n" +
					"          i.ET_MS + o.et_ms sum_et_ms,\n" +
					"          i.IS_CALL,\n" +
					"          i.LST_FLOW,\n" +
					"          NVL (i.PL_IN, o.PL_IN) PL_IN,\n" +
					"          NVL (i.PL_out, o.PL_out) PL_out,\n" +
					"          i.SRVC_CODE,\n" +
					"          i.SRVC_MSG,\n" +
					"          i.CDATE,\n" +
					"          i.CUSER,\n" +
					"          i.UDATE,\n" +
					"          i.UUSER\n" +
					"     FROM PRFX.GNL_EXT_SYST_CALL_LOG i, PRFX.GNL_EXT_SYST_CALL_LOG o\n" +
					"    WHERE     i.GNL_EXT_SYST_CALL_LOG_ID = o.log_corr_id(+)\n" +
					"          AND i.log_corr_id IS NULL\n" +
					"          AND i.TXN_ID = ? ORDER BY GNL_EXT_SYST_CALL_LOG_ID ASC";
					*/
            String query = "SELECT * FROM PRFX.GNL_EXT_SYST_CALL_LOG WHERE TXN_ID = ? ORDER BY GNL_EXT_SYST_CALL_LOG_ID ASC";

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                stmt.setString(1, custOrdId);
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

    public List<Map<String,String>> getOrdersOfProduct(Connection connection, Long custOrdId){
        String query = "select (SELECT SHRT_CODE FROM PRFX.ORD_ST WHERE ORD_ST_ID = CO.ORD_ST_ID) AS STATUS,"
                + "(SELECT SHRT_CODE FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_SHRT_CODE, "
                + "(SELECT EXT_SHRT_CODE FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_EXT_SHRT_CODE, "
                + "(SELECT NAME FROM PRFX.BSN_INTER_SPEC WHERE BSN_INTER_SPEC_ID = (SELECT BSN_INTER_SPEC_ID FROM PRFX.BSN_INTER WHERE BSN_INTER_ID = CO.BSN_INTER_ID) ) AS BI_NAME, "
                + "co.* from PRFX.cust_ord co where co.cust_ord_id in\n" +
                "(\n" +
                "select cust_ord_id from PRFX.cust_ord_item where prod_id = ?\n" +
                ") order by cust_ord_id desc";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), custOrdId);
    }

    public List<Map<String,String>> getOrderChars(Connection connection, Long custOrdId){
        String query =     "select gc.shrt_code,gc.name, ocv.char_id, ocv.char_val_id, ocv.val, ocv.cdate, ocv.udate\n" +
                " from PRFX.cust_ord_char_val ocv, PRFX.gnl_char gc where ocv.cust_ord_id = ? and ocv.char_id = gc.char_id and ocv.is_actv=1 order by ocv.char_id\n";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), custOrdId);
    }

    public List<Map<String,String>> getOrderItemChars(Connection connection, Long custOrdItemId){
        String query =     "select gc.shrt_code,gc.name, icv.char_id, icv.char_val_id, icv.val, icv.cdate, icv.udate\n" +
                " from PRFX.cust_ord_item_char_val icv, PRFX.gnl_char gc where icv.cust_ord_item_id = ? and icv.char_id = gc.char_id order by icv.char_id";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), custOrdItemId);
    }

    public List<Map<String,String>> getOrderBtnLogs(Connection connection, Long custOrdId){
        String query =     "select lo.BTN_DESCR, col.CDATE, lo.BTN_METHOD_NAME, lo.SHRT_CODE from PRFX.CUST_ORD_LOG col, PRFX.LOG_OBJ lo where \n" +
                " col.CUST_ORD_ID = ? and LO.LOG_OBJ_ID = COL.LOG_OBJ_ID order by COL.CUST_ORD_LOG_ID desc";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), custOrdId);
    }

    public List<Map<String,String>> getBsnInterRels(Connection connection, Long bsnInterId){
        String query =     "select (SELECT SHRT_CODE FROM PRFX.BSN_INTER_REL_TP WHERE BSN_INTER_REL_TP_ID = BREL.BSN_INTER_REL_TP_ID) AS TYPE, "
                + "BREL.* from PRFX.BSN_INTER_REL BREL where BREL.BSN_INTER_ID1 = ? AND IS_ACTV=1";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), bsnInterId);
    }

    public InputStream getServiceOut(Connection connection, Long logId){
        InputStream result = null;
        if((connection!=null)&&(logId!=null)){
            PreparedStatement stmt = null;
            ResultSet rs = null;
			/*String query =
			          "   SELECT   NVL (i.PL_out, o.PL_out) PL_out\n" +
			                  "    FROM PRFX.GNL_EXT_SYST_CALL_LOG i, PRFX.GNL_EXT_SYST_CALL_LOG o\n" +
			                  "    WHERE     i.GNL_EXT_SYST_CALL_LOG_ID = o.log_corr_id(+)\n" +
			                  "          AND i.log_corr_id IS NULL"
			                  + "        AND i.GNL_EXT_SYST_CALL_LOG_ID = ?";
			*/
            String query = "SELECT PL_OUT FROM PRFX.GNL_EXT_SYST_CALL_LOG WHERE PL_OUT IS NOT NULL"
                    + " AND (GNL_EXT_SYST_CALL_LOG_ID = ? OR LOG_CORR_ID = ?)";

            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                stmt.setLong(1, logId);
                stmt.setLong(2, logId);
                rs=stmt.executeQuery();
                if(rs.next()){
                    IOUtil ioUtil = new IOUtil();
                    result = ioUtil.convertReadertoInputStrream(rs.getClob("PL_OUT").getCharacterStream());
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

    public InputStream getServiceIn(Connection connection, Long logId){
        InputStream result = null;
        if((connection!=null)&&(logId!=null)){
            PreparedStatement stmt = null;
            ResultSet rs = null;
			/*String query =
			          "   SELECT   NVL (i.PL_IN, o.PL_IN) PL_IN\n" +
			                  "    FROM PRFX.GNL_EXT_SYST_CALL_LOG i, PRFX.GNL_EXT_SYST_CALL_LOG o\n" +
			                  "    WHERE     i.GNL_EXT_SYST_CALL_LOG_ID = o.log_corr_id(+)\n" +
			                  "          AND i.log_corr_id IS NULL"
			                  + "        AND i.GNL_EXT_SYST_CALL_LOG_ID = ?";
			*/
            String query = "SELECT PL_IN FROM PRFX.GNL_EXT_SYST_CALL_LOG WHERE PL_IN IS NOT NULL"
                    + " AND (GNL_EXT_SYST_CALL_LOG_ID = ? OR LOG_CORR_ID = ?)";
            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                stmt.setLong(1, logId);
                stmt.setLong(2, logId);
                rs=stmt.executeQuery();
                if(rs.next()){
                    IOUtil ioUtil = new IOUtil();
                    result = ioUtil.convertReadertoInputStrream(rs.getClob("PL_IN").getCharacterStream());
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

    public List<Map<String,String>> getServiceCalls(Connection connection, Map<String,String> stringParameters, Date startDate, Date endDate){
        List<Map<String,String>> result = new ArrayList<Map<String,String>>();
        if((connection!=null)&&(stringParameters!=null)&&(startDate!=null)&&(endDate!=null)){
            PreparedStatement stmt = null;
			/*String query ="   SELECT i.GNL_EXT_SYST_CALL_LOG_ID,\n" +
					"          i.GNL_EXT_WEB_SRVC_OPER_ID,\n" +
					"          i.GNL_EXT_SYST_CALL_QUEUE_ID,\n" +
					"          i.TXN_ID,\n" +
					"          i.SRVC_NAME,\n" +
					"          i.OPER_NAME,\n" +
					"          i.DESCR,\n" +
					"          i.ERR_MSG,\n" +
					"          i.ERR_CODE,\n" +
					"          i.ERR_DATE,\n" +
					"          i.CLIENT_IP,\n" +
					"          i.SRVR_NAME,\n" +
					"          NVL (o.LOG_CORR_ID, i.LOG_CORR_ID) LOG_CORR_ID,\n" +
					"          O.GNL_EXT_SYST_CALL_LOG_ID child_GNL_EXT_SYST_CALL_LOG_ID,\n" +
					"          i.ET_MS + o.et_ms sum_et_ms,\n" +
					"          i.IS_CALL,\n" +
					"          i.LST_FLOW,\n" +
					"          NVL (i.PL_IN, o.PL_IN) PL_IN,\n" +
					"          NVL (i.PL_out, o.PL_out) PL_out,\n" +
					"          i.SRVC_CODE,\n" +
					"          i.SRVC_MSG,\n" +
					"          i.CDATE,\n" +
					"          i.CUSER,\n" +
					"          i.UDATE,\n" +
					"          i.UUSER\n" +
					"     FROM PRFX.GNL_EXT_SYST_CALL_LOG i, PRFX.GNL_EXT_SYST_CALL_LOG o\n" +
					"    WHERE     i.GNL_EXT_SYST_CALL_LOG_ID = o.log_corr_id(+)\n" +
					"          AND i.log_corr_id IS NULL\n" +
					"          AND i.CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss')";
			*/
            String query = "SELECT * FROM PRFX.GNL_EXT_SYST_CALL_LOG WHERE CDATE BETWEEN to_date(?, 'dd-mm-yyyy hh24:mi:ss') and to_date(?, 'dd-mm-yyyy hh24:mi:ss')";

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String startDateStr = format.format(startDate);
            String endDateStr = format.format(endDate);

            if(stringParameters.get("CLIENTIP")!=null){
                query +="\n AND CLIENT_IP = ? ";
            }
            if(stringParameters.get("ERRCODE")!=null){
                query +="\n AND ERR_CODE = ? ";
            }
            if(stringParameters.get("ERRMSG")!=null){
                query +="\n AND ERR_MSG = ? ";
            }
            if(stringParameters.get("OPERNAME")!=null){
                query +="\n AND OPER_NAME = ? ";
            }
            if(stringParameters.get("SRVCCODE")!=null){
                query +="\n AND SRVC_CODE = ? ";
            }
            if(stringParameters.get("SRVCMSG")!=null){
                query +="\n AND SRVC_MSG = ? ";
            }
            if(stringParameters.get("SRVCNAME")!=null){
                query +="\n AND SRVC_NAME = ? ";
            }
            if(stringParameters.get("SRVRNAME")!=null){
                query +="\n AND SRVR_NAME = ? ";
            }
            if(stringParameters.get("TXN")!=null){
                query +="\n AND TXN_ID = ? ";
            }

            query +=  "\n ORDER BY GNL_EXT_SYST_CALL_LOG_ID ASC";
            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                int ndx = 1;
                stmt.setString(ndx++, startDateStr);
                stmt.setString(ndx++, endDateStr);
                if(stringParameters.get("CLIENTIP")!=null){
                    stmt.setString(ndx++, stringParameters.get("CLIENTIP"));
                }
                if(stringParameters.get("ERRCODE")!=null){
                    stmt.setString(ndx++, stringParameters.get("ERRCODE"));
                }
                if(stringParameters.get("ERRMSG")!=null){
                    stmt.setString(ndx++, stringParameters.get("ERRMSG"));
                }
                if(stringParameters.get("OPERNAME")!=null){
                    stmt.setString(ndx++, stringParameters.get("OPERNAME"));
                }
                if(stringParameters.get("SRVCCODE")!=null){
                    stmt.setString(ndx++, stringParameters.get("SRVCCODE"));
                }
                if(stringParameters.get("SRVCMSG")!=null){
                    stmt.setString(ndx++, stringParameters.get("SRVCMSG"));
                }
                if(stringParameters.get("SRVCNAME")!=null){
                    stmt.setString(ndx++, stringParameters.get("SRVCNAME"));
                }
                if(stringParameters.get("SRVRNAME")!=null){
                    stmt.setString(ndx++, stringParameters.get("SRVRNAME"));
                }
                if(stringParameters.get("TXN")!=null){
                    stmt.setString(ndx++, stringParameters.get("TXN"));
                }
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
}
