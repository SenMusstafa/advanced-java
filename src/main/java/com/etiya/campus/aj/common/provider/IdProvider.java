package com.etiya.campus.aj.common.provider;

import com.etiya.campus.aj.domain.common.Definitions;

public class IdProvider {
    public static String get(String parentType, String type){
        String result = "";
        if (type != null) {

            if (parentType != null && parentType.equals(Definitions.ObjectType.ORDERSOFPRODUCT.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                result = "PROD_ID";
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.BSNINTERREL.toString())
                    && type.equals(Definitions.ObjectType.BSNINTER.toString())) {
                result = "BSN_INTER_ID2";
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.BSNINTER.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                result = "BSN_INTER_ID";
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODREL.toString())
                    && type.equals(Definitions.ObjectType.PRODUCT.toString())) {
                result = "PROD_ID2";
            }  else if (parentType != null && parentType.equals(Definitions.ObjectType.POST_ACTN.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                result = "TRNSC_ID";
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODUCT.toString())
                    && type.equals(Definitions.ObjectType.CUSTOMER.toString())) {
                result = "PROD_ID";
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODUCT.toString())
                    && type.equals(Definitions.ObjectType.ADDRESS.toString())) {
                result = "PROD_ID";
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODSUBST.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                result = "BSN_INTER_ID";
            } else if (type.equals(Definitions.ObjectType.ORDER.toString())) {
                result = "CUST_ORD_ID";
            } else if (type.equals(Definitions.ObjectType.ORDER_ITEM.toString())) {
                result = "CUST_ORD_ID";
            } else if (type.equals(Definitions.ObjectType.PRODUCT.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.BSNINTER.toString())) {
                result = "BSN_INTER_ID";
            } else if (type.equals(Definitions.ObjectType.POST_ACTN.toString())) {
                result = "BSN_INTER_ID";
            } else if (type.equals(Definitions.ObjectType.SERVICE_CALL.toString())) {
                result = "CUST_ORD_ID";
            } else if (type.equals(Definitions.ObjectType.ORD_JMS.toString())) {
                result = "BSN_INTER_ID";
            } else if (type.equals(Definitions.ObjectType.CUSTOMER.toString())) {
                result = "CUST_ID";
            } else if (type.equals(Definitions.ObjectType.ORDERCHAR.toString())) {
                result = "CUST_ORD_ID";
            } else if (type.equals(Definitions.ObjectType.PRODUCTCHAR.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.PRODSUBST.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.PRODSUBSTEXE.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.ITEMCHAR.toString())) {
                result = "CUST_ORD_ITEM_ID";
            } else if (type.equals(Definitions.ObjectType.ITEMCHAR.toString())) {
                result = "CUST_ORD_ITEM_ID";
            } else if (type.equals(Definitions.ObjectType.PRODREL.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.ORDERBTNLOG.toString())) {
                result = "CUST_ORD_ID";
            } else if (type.equals(Definitions.ObjectType.PROD_OFR.toString())) {
                result = "PROD_OFR_ID";
            } else if (type.equals(Definitions.ObjectType.PROD_SPEC.toString())) {
                result = "PROD_SPEC_ID";
            } else if (type.equals(Definitions.ObjectType.SRVC_SPEC.toString())) {
                result = "PROD_SPEC_ID";
            } else if (type.equals(Definitions.ObjectType.RSRC_SPEC.toString())) {
                result = "PROD_SPEC_ID";
            } else if (type.equals(Definitions.ObjectType.PS_CHAR_USE.toString())) {
                result = "PROD_SPEC_ID";
            } else if (type.equals(Definitions.ObjectType.WS_MSG_IN.toString())) {
                result = "GNL_EXT_SYST_CALL_LOG_ID";
            } else if (type.equals(Definitions.ObjectType.WS_MSG_OUT.toString())) {
                result = "GNL_EXT_SYST_CALL_LOG_ID";
            } else if (type.equals(Definitions.ObjectType.PRODUCTBYHN.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.PRODUCTBYPRODNUM.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.PRODUCTBYSRLNUM.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.ORDER_ST.toString())) {
                result = "CUST_ORD_ID";
            } else if (type.equals(Definitions.ObjectType.ITEM_ST.toString())) {
                result = "CUST_ORD_ITEM_ID";
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODUCT.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                result = "PROD_ID";
            } else if (type.equals(Definitions.ObjectType.BSNINTERREL.toString())) {
                result = "BSN_INTER_ID";
            }

        }
        return result;
    }
}
