package com.etiya.campus.aj.domain.order;

import com.etiya.campus.aj.common.container.ApplicationService;
import com.etiya.campus.aj.common.container.DomainObjectWrapper;
import com.etiya.campus.aj.db.ConnectionManager;
import com.etiya.campus.aj.db.dao.CustomerOrderDAO;
import com.etiya.campus.aj.domain.common.Definitions;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerOrderTest {
    public static void main(String[] args){
        CustomerOrderItemUtility utility = new CustomerOrderItemUtility();
        CustomerOrder order = new CustomerOrder();
        CustomerOrderItem item1 = new CustomerOrderItem();
        item1.setStatus(Definitions.OrderItemStatus.DEL.toString());
        CustomerOrderItem item2 = new CustomerOrderItem();
        item2.setStatus(Definitions.OrderItemStatus.FINISHED.toString());
        CustomerOrderItem item3 = new CustomerOrderItem();
        item3.setStatus(Definitions.OrderItemStatus.DISPATCED.toString());
        order.getItemList().add(item1);
        order.getItemList().add(item3);
        order.getItemList().add(item2);
        //item2.setStatus(null);
        System.out.println(Definitions.OrderItemStatus.DISPATCED.toString().equals(item2.getStatus()));
        System.out.println(item2.getStatus().equals(Definitions.OrderItemStatus.DISPATCED.toString()));
       List<CustomerOrderItem> dispatchedList = order.getItemList().
                stream()
                .filter(utility::isItemDispatched).peek(System.out::println).collect(Collectors.toList());
        //System.out.println(dispatchedList);
        //queryCustomerOrder();
        testDomainObject();
    }

    private static void queryCustomerOrder(){
        ConnectionManager cm = new ConnectionManager();
        cm.init("D:\\work\\Egitim\\advanced\\db\\PSTNTESTProfile.txt");
        Connection connection = cm.getConnection(true);
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
        customerOrderDAO.setTablePrefix(cm.getTablePrefix());
        Map<String,String> result = customerOrderDAO.getCustomerOrderInfo(connection,902013100710143361L);
        //System.out.println(result);
        String custId = result.entrySet().stream().peek(e->System.out.println("[ "+e.getKey()+" - "+e.getValue()+" ]"))
                .filter(e->e.getKey().equals("RLTD_CUST_ID")).map(Map.Entry::getValue).collect(Collectors.joining());
        System.out.println(custId);
        cm.closeConnection();
    }
    private static void testDomainObject(){
        ConnectionManager cm = new ConnectionManager();
        cm.init("D:\\work\\Egitim\\advanced\\db\\PSTNTESTProfile.txt");
        Connection connection = cm.getConnection(true);
        ApplicationService applicationService = new ApplicationService();
        applicationService.setConnectionManager(cm);
        DomainObjectWrapper object = applicationService.searchByType( "902013100710143361", Definitions.ObjectType.ORDER.toString(), null);
        object.loadSubItems();
        List<DomainObjectWrapper> items = object.getChildObjects().stream().filter((dow)->Definitions.ObjectType.ORDER_ITEM.toString().equals(dow.getType())).collect(Collectors.toList());
        System.out.println(object.getProperties());
        System.out.println(items);
    }





}
