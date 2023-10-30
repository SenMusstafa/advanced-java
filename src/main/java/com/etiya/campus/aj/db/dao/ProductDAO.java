package com.etiya.campus.aj.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO extends BaseDAO {
    public Map<String,String> getProductInfo(Connection connection, Long productId){
        Map<String,String> result = new HashMap<String,String>();
        if((connection!=null)&&(productId!=null)){
            PreparedStatement stmt = null;
            String query = "SELECT (SELECT SHRT_CODE FROM PRFX.GNL_ST WHERE GNL_ST_ID = P.ST_ID) AS STATUS,"
                    + " P.* FROM PRFX.PROD P WHERE PROD_ID = ?";
            try{
                stmt = connection.prepareStatement(this.applyCurrentPrefix(query));
                stmt.setLong(1, productId);
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

    public List<Map<String,String>> getProdRels(Connection connection, Long prodId){
        String query =     "SELECT GnlRoleSpec.NAME,\n" +
                "       GnlRoleSpec.SHRT_CODE AS SHRT_CODE1, \n" +
                "       ProdRel.PROD_ID2, \n" +
                "       ProdRel.PROD_ID1, \n" +
                "       ProdRel.REL_TP_ID, \n" +
                "       ProdRel.GNL_ROLE_SPEC_ID, \n" +
                "       ProdRel.CDATE, \n" +
                "       ProdRel.CUSER, \n" +
                "       ProdRel.EDATE, \n" +
                "       ProdRel.PROD_REL_ID, \n" +
                "       ProdRel.SDATE, \n" +
                "       ProdRel.UDATE, \n" +
                "       ProdRel.UUSER, \n" +
                "       GnlRoleSpec.IS_GOV_ADDR, \n" +
                "       GnlRoleSpec.IS_GOV_OWNER, \n" +
                "       ProdRel.TRNSC_ID, \n" +
                "       ProdRel.IS_NOT_SPRT\n" +
                "FROM PRFX.PROD_REL ProdRel, PRFX.GNL_ROLE_SPEC GnlRoleSpec\n" +
                "WHERE ProdRel.GNL_ROLE_SPEC_ID=GnlRoleSpec.GNL_ROLE_SPEC_ID\n" +
                "AND ProdRel.is_Actv =1 AND ProdRel.prod_id1=?";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), prodId);
    }

    public List<Map<String,String>> getProductChars(Connection connection, Long prodId){
        String query =     "select gc.shrt_code,gc.name, pc.char_id, pc.char_val_id, pc.val, pc.trnsc_id, pc.is_srch, pc.cdate, pc.udate\n" +
                " from PRFX.prod_char_val pc, PRFX.gnl_char gc where pc.prod_id = ? and pc.char_id = gc.char_id\n" +
                " and ST_ID = (select gnl_st_id from PRFX.gnl_st where shrt_code = 'ACTV' and ent_code_name = 'PROD_CHAR_VAL') order by pc.char_id";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), prodId);
    }

    public Map<String,String> getProductOfferInfo(Connection connection, Long prodOfrId){
        String query = "SELECT * FROM PRFX.PROD_OFR WHERE PROD_OFR_ID = ? AND "
                + "ST_ID = (SELECT GNL_ST_ID FROM PRFX.GNL_ST WHERE SHRT_CODE = 'ACTV' AND ENT_CODE_NAME = 'PROD_OFR')";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), prodOfrId);
    }

    public Map<String,String> getProductSpecInfo(Connection connection, Long prodSpecId){
        String query = "SELECT * FROM PRFX.PROD_SPEC WHERE PROD_SPEC_ID = ? AND "
                + "ST_ID = (SELECT GNL_ST_ID FROM PRFX.GNL_ST WHERE SHRT_CODE = 'ACTV' AND ENT_CODE_NAME = 'PROD_SPEC')";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), prodSpecId);
    }

    public Map<String,String> getServiceSpecInfo(Connection connection, Long prodSpecId){
        String query = "SELECT * FROM PRFX.SRVC_SPEC WHERE "
                + "SRVC_SPEC_ID = (SELECT SRVC_SPEC_ID FROM PRFX.PROD_SPEC_SRVC_SPEC WHERE PROD_SPEC_ID = ? AND "
                + "ST_ID = (SELECT GNL_ST_ID FROM PRFX.GNL_ST WHERE SHRT_CODE = 'ACTV' AND ENT_CODE_NAME = 'PROD_SPEC_SRVC_SPEC') ) AND "
                + "ST_ID = (SELECT GNL_ST_ID FROM PRFX.GNL_ST WHERE SHRT_CODE = 'ACTV' AND ENT_CODE_NAME = 'SRVC_SPEC')";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), prodSpecId);
    }

    public Map<String,String> getResourceSpecInfo(Connection connection, Long prodSpecId){
        String query = "SELECT * FROM PRFX.RSRC_SPEC WHERE "
                + "RSRC_SPEC_ID = (SELECT RSRC_SPEC_ID FROM PRFX.PROD_SPEC_RSRC_SPEC WHERE PROD_SPEC_ID = ? AND "
                + "ST_ID = (SELECT GNL_ST_ID FROM PRFX.GNL_ST WHERE SHRT_CODE = 'ACTV' AND ENT_CODE_NAME = 'PROD_SPEC_RSRC_SPEC') ) AND "
                + "ST_ID = (SELECT GNL_ST_ID FROM PRFX.GNL_ST WHERE SHRT_CODE = 'ACTV' AND ENT_CODE_NAME = 'RSRC_SPEC')";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), prodSpecId);
    }

    public List<Map<String,String>> getProdSpecCharUse(Connection connection, Long prodSpecId){
        String query = "SELECT * FROM PRFX.PROD_SPEC_CHAR_USE PSCU WHERE PSCU.PROD_SPEC_ID = ? AND \n" +
                "PSCU.ST_ID = (SELECT GNL_ST_ID FROM PRFX.GNL_ST WHERE SHRT_CODE = 'ACTV' AND ENT_CODE_NAME = 'PROD_SPEC_CHAR_USE')";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), prodSpecId);
    }

    public List<Map<String,String>> getProdByHn(Connection connection, String hn){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.GNL_ST WHERE GNL_ST_ID = P.ST_ID) AS STATUS,"
                + "P.* FROM PRFX.PROD P WHERE P.IDENT_VAL1 = ?";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), hn);
    }

    public List<Map<String,String>> getProdByProdNum(Connection connection, String prodNum){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.GNL_ST WHERE GNL_ST_ID = P.ST_ID) AS STATUS,"
                + "P.* FROM PRFX.PROD P WHERE P.PROD_NUM = ?";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), prodNum);
    }

    public List<Map<String,String>> getProdBySrlNum(Connection connection, String srlNum){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.GNL_ST WHERE GNL_ST_ID = P.ST_ID) AS STATUS,"
                + "P.* FROM PRFX.PROD P WHERE P.PROD_SRL_NUM = ?";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), srlNum);
    }

    public List<Map<String,String>> getProdSubSts(Connection connection, Long prodId){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.GNL_ST WHERE GNL_ST_ID = PSS.ST_ID AND ENT_CODE_NAME = 'PROD_SUB_ST') AS STATUS,"
                + "PSS.* FROM PRFX.PROD_SUB_ST PSS WHERE PSS.PROD_ID = ? ORDER BY PROD_SUB_ST_ID DESC";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), prodId);
    }

    public List<Map<String,String>> getProdSubStExes(Connection connection, Long prodId){
        String query = "SELECT (SELECT SHRT_CODE FROM PRFX.GNL_ST WHERE GNL_ST_ID = PSS.ST_ID AND ENT_CODE_NAME = 'PROD_SUB_ST_EXE_ST') AS STATUS,"
                + "(SELECT SHRT_CODE FROM PRFX.GNL_ST WHERE GNL_ST_ID = PSS.PROC_ST_ID AND ENT_CODE_NAME = 'PROD_SUB_ST_EXE_PROC_ST') AS PROC_STATUS,"
                + "PSS.* FROM PRFX.PROD_SUB_ST_EXE PSS WHERE PSS.PROD_ID = ? ORDER BY PROD_SUB_ST_EXE_ID DESC";
        return this.executeQueryWithSingleParameterForListResult(connection, this.applyCurrentPrefix(query), prodId);
    }
}
