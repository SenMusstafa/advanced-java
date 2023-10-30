package com.etiya.campus.aj.db.dao;

import java.sql.Connection;
import java.util.Map;

public class CustomerDAO extends BaseDAO {
    public Map<String,String> getCustomerByProductId(Connection connection, Long productId){
        String query = "SELECT CU.CUST_ID, CU.EXT_CUST_ID, CU.CUST_TP_ID, CU.IS_ISS, CU.ST_ID, P.PARTY_ID, P.PARTY_TP_ID, PR.PARTY_ROLE_ID, PR.PARTY_ROLE_TP_ID\n" +
                "FROM CUST CU, PROD_INVL_ROLE PIR, PARTY P, PARTY_ROLE PR\n" +
                "WHERE PR.PARTY_ROLE_ID = PIR.INVL_ROLE_ID\n" +
                "AND PR.PARTY_ROLE_ID = CU.PARTY_ROLE_ID\n" +
                "AND PR.PARTY_ID = P.PARTY_ID\n" +
                "AND PIR.PROD_ID = ?";
        return this.executeQueryWithSingleParameterForSingleResult(connection, this.applyCurrentPrefix(query), productId);
    }
}
