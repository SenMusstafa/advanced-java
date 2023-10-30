package com.etiya.campus.aj.domain.common;

public final class Definitions {
    public enum BsnInterSpec {
        REAL_SALE,
        ACT_CANCEL,
        RL_CHG_PKG,
        REAL_MOVE;
    }

    public enum OrderFlow {
        SELECT_OFFER,
        PROD_CONF,
        APPOINTMENT,
        SHIPMENT,
        BILLING,
        SUMMARY;
    }

    public enum OrderItemStatus{
        DISPATCED,
        DEL,
        FINISHED;
    }

    public enum ObjectType{
        ORDER,
        BSNINTER,
        BSNINTERRELS,
        BSNINTERREL,
        SERVICE_CALLS,
        SERVICE_CALL,
        POST_ACTIONS,
        POST_ACTN,
        ORD_JMS,
        CUSTOMER,
        CUST_ACCTS,
        CUST_ACCT,
        CUST_CONTRS,
        CUST_CONTR,
        ITEMS,
        ORDER_ITEM,
        PRODUCT,PRODUCTBYSRLNUM,PRODUCTBYPRODNUM,PRODUCTBYHN,
        PROD_OFR,
        PROD_SPEC,
        SRVC_SPEC,
        RSRC_SPEC,
        ITEMCHARS,
        ITEMCHAR,
        ORDERCHARS,
        ORDERCHAR,
        PRODUCTCHARS,
        PRODUCTCHAR,
        PRODREL,
        PRODRELS,
        PRODSUBSTS,
        PRODSUBST,
        PRODSUBSTEXES,
        PRODSUBSTEXE,
        ORDERSOFPRODUCT,
        ORDERBTNLOGS,
        ORDERBTNLOG,
        PS_CHAR_USES,
        PS_CHAR_USE,
        WS_MSG_IN,
        WS_MSG_OUT,
        WS_MSG_IN_SAVE,
        WS_MSG_OUT_SAVE,
        ORDER_ST_HSTR,
        ORDER_ST,
        ITEM_ST_HSTR,
        ITEM_ST,
        JOBRUNLOG,
        JOBRUNLOGITEM,
        JOBPROCESS,
        JOBPROCESSITEM,
        ADDRESS,
        ACCOUNT_INVLS,
        ACCOUNT_INVL
        ;
    }

    public enum RelType{
        ONETOONE,
        ONETOMANY,
        ABSTRACT;
    }

    public enum SearchType{
        LIST,
        SINGLE;
    }
}
