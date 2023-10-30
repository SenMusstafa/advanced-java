package com.etiya.campus.aj.common.provider;

import com.etiya.campus.aj.domain.common.Definitions;

import java.util.ArrayList;
import java.util.List;

public class RelationProvider {
    public static List<String> get(String type){
        List<String> result = new ArrayList<String>();
        if (type != null) {
            if (type.equals(Definitions.ObjectType.ORDER.toString())) {
                result.add(Definitions.ObjectType.ITEMS.toString());
                result.add(Definitions.ObjectType.CUSTOMER.toString());
                result.add(Definitions.ObjectType.ORDERCHARS.toString());
                result.add(Definitions.ObjectType.ORD_JMS.toString());
                result.add(Definitions.ObjectType.POST_ACTIONS.toString());
                result.add(Definitions.ObjectType.SERVICE_CALLS.toString());
                result.add(Definitions.ObjectType.ORDERBTNLOGS.toString());
                result.add(Definitions.ObjectType.BSNINTERRELS.toString());
                result.add(Definitions.ObjectType.ORDER_ST_HSTR.toString());
            } else if (type.equals(Definitions.ObjectType.ORDER_ITEM.toString())) {
                result.add(Definitions.ObjectType.PRODUCT.toString());
                result.add(Definitions.ObjectType.ITEMCHARS.toString());
                result.add(Definitions.ObjectType.ITEM_ST_HSTR.toString());
            } else if (type.equals(Definitions.ObjectType.ITEMS.toString())) {
                result.add(Definitions.ObjectType.ORDER_ITEM.toString());
            } else if (type.equals(Definitions.ObjectType.POST_ACTIONS.toString())) {
                result.add(Definitions.ObjectType.POST_ACTN.toString());
            } else if (type.equals(Definitions.ObjectType.SERVICE_CALLS.toString())) {
                result.add(Definitions.ObjectType.SERVICE_CALL.toString());
            } else if (type.equals(Definitions.ObjectType.PRODUCT.toString())) {
                result.add(Definitions.ObjectType.PRODSUBSTS.toString());
                result.add(Definitions.ObjectType.PRODSUBSTEXES.toString());
                result.add(Definitions.ObjectType.PRODRELS.toString());
                result.add(Definitions.ObjectType.PRODUCTCHARS.toString());
                result.add(Definitions.ObjectType.ORDERSOFPRODUCT.toString());
                result.add(Definitions.ObjectType.PROD_OFR.toString());
                result.add(Definitions.ObjectType.PROD_SPEC.toString());
                result.add(Definitions.ObjectType.CUSTOMER.toString());
                result.add(Definitions.ObjectType.ADDRESS.toString());
            } else if (type.equals(Definitions.ObjectType.PRODRELS.toString())) {
                result.add(Definitions.ObjectType.PRODREL.toString());
            } else if (type.equals(Definitions.ObjectType.ORDERSOFPRODUCT.toString())) {
                result.add(Definitions.ObjectType.ORDER.toString());
            } else if (type.equals(Definitions.ObjectType.PRODUCTCHARS.toString())) {
                result.add(Definitions.ObjectType.PRODUCTCHAR.toString());
            } else if (type.equals(Definitions.ObjectType.ITEMCHARS.toString())) {
                result.add(Definitions.ObjectType.ITEMCHAR.toString());
            } else if (type.equals(Definitions.ObjectType.ORDERCHARS.toString())) {
                result.add(Definitions.ObjectType.ORDERCHAR.toString());
            } else if (type.equals(Definitions.ObjectType.ORDERBTNLOGS.toString())) {
                result.add(Definitions.ObjectType.ORDERBTNLOG.toString());
            } else if (type.equals(Definitions.ObjectType.PROD_SPEC.toString())) {
                result.add(Definitions.ObjectType.SRVC_SPEC.toString());
                result.add(Definitions.ObjectType.RSRC_SPEC.toString());
                result.add(Definitions.ObjectType.PS_CHAR_USES.toString());
            } else if (type.equals(Definitions.ObjectType.PS_CHAR_USES.toString())) {
                result.add(Definitions.ObjectType.PS_CHAR_USE.toString());
            } else if (type.equals(Definitions.ObjectType.BSNINTERRELS.toString())) {
                result.add(Definitions.ObjectType.BSNINTERREL.toString());
            } else if (type.equals(Definitions.ObjectType.BSNINTERREL.toString())) {
                result.add(Definitions.ObjectType.BSNINTER.toString());
            } else if (type.equals(Definitions.ObjectType.BSNINTER.toString())) {
                result.add(Definitions.ObjectType.ORDER.toString());
            } else if (type.equals(Definitions.ObjectType.PRODSUBSTS.toString())) {
                result.add(Definitions.ObjectType.PRODSUBST.toString());
            }  else if (type.equals(Definitions.ObjectType.PRODSUBSTEXES.toString())) {
                result.add(Definitions.ObjectType.PRODSUBSTEXE.toString());
            } else if (type.equals(Definitions.ObjectType.PRODREL.toString())) {
                result.add(Definitions.ObjectType.PRODUCT.toString());
            } else if (type.equals(Definitions.ObjectType.POST_ACTN.toString())) {
                result.add(Definitions.ObjectType.ORDER.toString());
            } else if (type.equals(Definitions.ObjectType.ORDER_ST_HSTR.toString())) {
                result.add(Definitions.ObjectType.ORDER_ST.toString());
            } else if (type.equals(Definitions.ObjectType.ITEM_ST_HSTR.toString())) {
                result.add(Definitions.ObjectType.ITEM_ST.toString());
            } else if(type.equals(Definitions.ObjectType.PRODSUBST.toString())) {
                result.add(Definitions.ObjectType.ORDER.toString());
            }

        }
        return result;
    }

    public static String geRelType(String type, String childType){
        String result = Definitions.RelType.ONETOONE.toString();
        if ((type != null) && (childType != null)) {
            if (((type.equals(Definitions.ObjectType.ITEMS.toString()))
                    && (childType.equals(Definitions.ObjectType.ORDER_ITEM.toString())))
                    || ((type.equals(Definitions.ObjectType.POST_ACTIONS.toString()))
                    && (childType.equals(Definitions.ObjectType.POST_ACTN.toString())))
                    || ((type.equals(Definitions.ObjectType.PRODRELS.toString()))
                    && (childType.equals(Definitions.ObjectType.PRODREL.toString())))
                    || ((type.equals(Definitions.ObjectType.ORDERCHARS.toString()))
                    && (childType.equals(Definitions.ObjectType.ORDERCHAR.toString())))
                    || ((type.equals(Definitions.ObjectType.PRODUCTCHARS.toString()))
                    && (childType.equals(Definitions.ObjectType.PRODUCTCHAR.toString())))
                    || ((type.equals(Definitions.ObjectType.ITEMCHARS.toString()))
                    && (childType.equals(Definitions.ObjectType.ITEMCHAR.toString())))
                    || ((type.equals(Definitions.ObjectType.ITEM_ST_HSTR.toString()))
                    && (childType.equals(Definitions.ObjectType.ITEM_ST.toString())))
                    || ((type.equals(Definitions.ObjectType.ORDER_ST_HSTR.toString()))
                    && (childType.equals(Definitions.ObjectType.ORDER_ST.toString())))
                    || ((type.equals(Definitions.ObjectType.ORDERSOFPRODUCT.toString()))
                    && (childType.equals(Definitions.ObjectType.ORDER.toString())))
                    || ((type.equals(Definitions.ObjectType.PS_CHAR_USES.toString()))
                    && (childType.equals(Definitions.ObjectType.PS_CHAR_USE.toString())))
                    || ((type.equals(Definitions.ObjectType.ORDERBTNLOGS.toString()))
                    && (childType.equals(Definitions.ObjectType.ORDERBTNLOG.toString())))
                    || ((type.equals(Definitions.ObjectType.BSNINTERRELS.toString()))
                    && (childType.equals(Definitions.ObjectType.BSNINTERREL.toString())))
                    || ((type.equals(Definitions.ObjectType.PRODSUBSTS.toString()))
                    && (childType.equals(Definitions.ObjectType.PRODSUBST.toString())))
                    || ((type.equals(Definitions.ObjectType.PRODSUBSTEXES.toString()))
                    && (childType.equals(Definitions.ObjectType.PRODSUBSTEXE.toString())))
                    || ((type.equals(Definitions.ObjectType.SERVICE_CALLS.toString()))
                    && (childType.equals(Definitions.ObjectType.SERVICE_CALL.toString())))) {
                result = Definitions.RelType.ONETOMANY.toString();

            } else if ((childType.equals(Definitions.ObjectType.ITEMS.toString()))
                    || (childType.equals(Definitions.ObjectType.POST_ACTIONS.toString()))
                    || (childType.equals(Definitions.ObjectType.PRODRELS.toString()))
                    || (childType.equals(Definitions.ObjectType.ORDERSOFPRODUCT.toString()))
                    || (childType.equals(Definitions.ObjectType.ORDERCHARS.toString()))
                    || (childType.equals(Definitions.ObjectType.PRODUCTCHARS.toString()))
                    || (childType.equals(Definitions.ObjectType.ITEMCHARS.toString()))
                    || (childType.equals(Definitions.ObjectType.ORDERBTNLOGS.toString()))
                    || (childType.equals(Definitions.ObjectType.PS_CHAR_USES.toString()))
                    || (childType.equals(Definitions.ObjectType.BSNINTERRELS.toString()))
                    || (childType.equals(Definitions.ObjectType.PRODSUBSTS.toString()))
                    || (childType.equals(Definitions.ObjectType.ORDER_ST_HSTR.toString()))
                    || (childType.equals(Definitions.ObjectType.ITEM_ST_HSTR.toString()))
                    || (childType.equals(Definitions.ObjectType.PRODSUBSTEXES.toString()))
                    || (childType.equals(Definitions.ObjectType.SERVICE_CALLS.toString()))) {
                result = Definitions.RelType.ABSTRACT.toString();
            }
        }
        return result;
    }
}
