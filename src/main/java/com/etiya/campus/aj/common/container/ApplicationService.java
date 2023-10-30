package com.etiya.campus.aj.common.container;

import com.etiya.campus.aj.common.provider.IdProvider;
import com.etiya.campus.aj.db.ConnectionManager;
import com.etiya.campus.aj.db.dao.CommonDAO;
import com.etiya.campus.aj.db.dao.CustomerDAO;
import com.etiya.campus.aj.db.dao.CustomerOrderDAO;
import com.etiya.campus.aj.db.dao.ProductDAO;
import com.etiya.campus.aj.domain.common.Definitions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ApplicationService {
    private ConnectionManager connectionManager;
    private boolean autoClose;

    public boolean isAutoClose() {
        return autoClose;
    }

    public void setAutoClose(boolean autoClose) {
        this.autoClose = autoClose;
    }

    public DomainObjectWrapper searchByType(String id, String type, String parentType) {
        DomainObjectWrapper foundObject = null;
        if ((id != null) && (type != null) && (connectionManager != null)) {
            ProductDAO productDAO = new ProductDAO();
            CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
            customerOrderDAO.setTablePrefix(this.connectionManager.getTablePrefix());
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.setTablePrefix(this.connectionManager.getTablePrefix());
            CommonDAO commonDAO = new CommonDAO();
            commonDAO.setTablePrefix(this.connectionManager.getTablePrefix());
            if (parentType != null && parentType.equals(Definitions.ObjectType.BSNINTER.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                Map<String, String> result = customerOrderDAO.getCustomerOrderByBsnInter(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODSUBST.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                Map<String, String> result = customerOrderDAO.getCustomerOrderByBsnInter(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.POST_ACTN.toString())
                    && type.equals(Definitions.ObjectType.ORDER.toString())) {
                Map<String, String> result = customerOrderDAO.getCustomerOrderInfo(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.BSNINTERREL.toString())
                    && type.equals(Definitions.ObjectType.BSNINTER.toString())) {
                Map<String, String> result = customerOrderDAO.getBsnInter(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (type.equals(Definitions.ObjectType.ORDER.toString())) {
                Map<String, String> result = customerOrderDAO.getCustomerOrderInfo(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (type.equals(Definitions.ObjectType.PRODUCT.toString())) {
                Map<String, String> result = productDAO.getProductInfo(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (type.equals(Definitions.ObjectType.PROD_OFR.toString())) {
                Map<String, String> result = productDAO.getProductOfferInfo(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (type.equals(Definitions.ObjectType.PROD_SPEC.toString())) {
                Map<String, String> result = productDAO.getProductSpecInfo(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (type.equals(Definitions.ObjectType.SRVC_SPEC.toString())) {
                Map<String, String> result = productDAO.getServiceSpecInfo(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (type.equals(Definitions.ObjectType.RSRC_SPEC.toString())) {
                Map<String, String> result = productDAO.getResourceSpecInfo(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODUCT.toString())
                    && type.equals(Definitions.ObjectType.CUSTOMER.toString())) {
                Map<String, String> result = customerDAO.getCustomerByProductId(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }
            } else if (parentType != null && parentType.equals(Definitions.ObjectType.PRODUCT.toString())
                    && type.equals(Definitions.ObjectType.ADDRESS.toString())) {
                Map<String, String> result = commonDAO.getAddressByProductId(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((result != null) && (result.size() > 0)) {
                    foundObject = new DomainObjectWrapper(result, type, id);
                }

            }
        }
        if(autoClose)this.connectionManager.closeConnection();
        return foundObject;
    }
    public List<DomainObjectWrapper> searchListByType(String id, String type){
        List<DomainObjectWrapper> result = new ArrayList<DomainObjectWrapper>();
        DomainObjectWrapper resultItem = null;
        if((id!=null)&&(type!=null)&&(this.connectionManager!=null)){
            CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
            customerOrderDAO.setTablePrefix(this.connectionManager.getTablePrefix());
            ProductDAO productDAO = new ProductDAO();
            productDAO.setTablePrefix(this.connectionManager.getTablePrefix());
            if (type.equals(Definitions.ObjectType.ORDER_ITEM.toString())) {
                List<Map<String, String>> items = customerOrderDAO.getCustomerOrderItems(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((items != null) && (items.size() > 0)) {
                    for (Map<String, String> item : items) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.ORDER_ITEM.toString(),
                                item.get("CUST_ORD_ITEM_ID"));
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.POST_ACTN.toString())) {
                List<Map<String, String>> postActions = customerOrderDAO.getPostActions(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((postActions != null) && (postActions.size() > 0)) {
                    for (Map<String, String> item : postActions) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.POST_ACTN.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.SERVICE_CALL.toString())) {
                List<Map<String, String>> serviceCalls = customerOrderDAO.getServiceCalls(this.connectionManager.getConnection(true), id);
                List<Map<String, String>> mergedServiceCallMessages = mergeServiceCallMessages(serviceCalls);
                if ((mergedServiceCallMessages != null) && (mergedServiceCallMessages.size() > 0)) {
                    for (Map<String, String> item : mergedServiceCallMessages) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.SERVICE_CALL.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PRODREL.toString())) {
                List<Map<String, String>> prodRels = productDAO.getProdRels(this.connectionManager.getConnection(true), Long.valueOf(id));
                if ((prodRels != null) && (prodRels.size() > 0)) {
                    for (Map<String, String> item : prodRels) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PRODREL.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.ORDER.toString())) {
                // ileride baska bir parametre ile orderlar sorgulanabilir
                //bu nedenle order + tip ile sorgulamaliyizi ya da parenttype+type ile
                List<Map<String, String>> ordersOfProduct = customerOrderDAO.getOrdersOfProduct(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((ordersOfProduct != null) && (ordersOfProduct.size() > 0)) {
                    for (Map<String, String> item : ordersOfProduct) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.ORDER.toString(), item.get("CUST_ORD_ID"));
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.ORDERCHAR.toString())) {
                List<Map<String, String>> list = customerOrderDAO.getOrderChars(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.ORDERCHAR.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.ITEMCHAR.toString())) {
                List<Map<String, String>> list = customerOrderDAO.getOrderItemChars(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.ITEMCHAR.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PRODUCTCHAR.toString())) {
                List<Map<String, String>> list = productDAO.getProductChars(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PRODUCTCHAR.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.ORDERBTNLOG.toString())) {
                List<Map<String, String>> list = customerOrderDAO.getOrderBtnLogs(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.ORDERBTNLOG.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PS_CHAR_USE.toString())) {
                List<Map<String, String>> list = productDAO.getProdSpecCharUse(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PS_CHAR_USE.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PRODUCTBYHN.toString())) {
                List<Map<String, String>> list = productDAO.getProdByHn(this.connectionManager.getConnection(true),
                        id);
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PRODUCT.toString(), item.get("PROD_ID"));
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PRODUCTBYPRODNUM.toString())) {
                List<Map<String, String>> list = productDAO.getProdByProdNum(this.connectionManager.getConnection(true),
                        id);
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PRODUCT.toString(), item.get("PROD_ID"));
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PRODUCTBYSRLNUM.toString())) {
                List<Map<String, String>> list = productDAO.getProdBySrlNum(this.connectionManager.getConnection(true),
                        id);
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PRODUCT.toString(), item.get("PROD_ID"));
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.BSNINTERREL.toString())) {
                List<Map<String, String>> list = customerOrderDAO.getBsnInterRels(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.BSNINTERREL.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PRODSUBST.toString())) {
                List<Map<String, String>> list = productDAO.getProdSubSts(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PRODSUBST.toString(), id);
                        result.add(resultItem);
                    }
                }
            } else if (type.equals(Definitions.ObjectType.PRODSUBSTEXE.toString())) {
                List<Map<String, String>> list = productDAO.getProdSubStExes(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.PRODSUBSTEXE.toString(), id);
                        result.add(resultItem);
                    }
                }
            }  else if (type.equals(Definitions.ObjectType.ORDER_ST.toString())) {
                List<Map<String, String>> list = customerOrderDAO.getOrderStHstr(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.ORDER_ST.toString(), id);
                        result.add(resultItem);
                    }
                }
            }  else if (type.equals(Definitions.ObjectType.ITEM_ST.toString())) {
                List<Map<String, String>> list = customerOrderDAO.getOrderItemStHstr(this.connectionManager.getConnection(true),
                        Long.valueOf(id));
                if ((list != null) && (list.size() > 0)) {
                    for (Map<String, String> item : list) {
                        resultItem = new DomainObjectWrapper(item, Definitions.ObjectType.ITEM_ST.toString(), id);
                        result.add(resultItem);
                    }
                }
            }
        }
        if(autoClose)this.connectionManager.closeConnection();
        return result;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private List<Map<String, String>> mergeServiceCallMessages(List<Map<String, String>> messages){
        List<Map<String, String>> mergedServiceCallMessages = new ArrayList<Map<String, String>>();
        List<String> processedMessages = new ArrayList<String>();
        if((messages!=null)&&(messages.size()>0)){
            for (Map<String, String> message : messages) {
                String logCorrId = message.get("LOG_CORR_ID");
                if((logCorrId!=null)&&(!logCorrId.trim().equals(""))){
                    mergedServiceCallMessages.add(message);
                    processedMessages.add( message.get("LOG_CORR_ID"));
                    processedMessages.add( message.get("GNL_EXT_SYST_CALL_LOG_ID"));
                }
            }

            //islenmemis varsa yani LOG_CORR_ID eslesmeyen onlari da ekleyelim
            for (Map<String, String> message : messages) {
                String logId = message.get("GNL_EXT_SYST_CALL_LOG_ID");
                if((logId!=null)&&(!logId.trim().equals(""))){
                    if(!processedMessages.contains(logId))
                        mergedServiceCallMessages.add(message);
                }
            }
        }
        return mergedServiceCallMessages;
    }
}
