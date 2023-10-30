package com.etiya.campus.aj.mutlithreading;

import com.etiya.campus.aj.domain.order.CustomerOrder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionExample {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------------------------------");
        Queue<CustomerOrder> orderQueue = new LinkedList<>();
        Thread orderCreator = new Thread(new OrderCreator(orderQueue));
        orderCreator.setName("OrderCreator-1");
        Thread orderCreator2 = new Thread(new OrderCreator(orderQueue));
        orderCreator2.setName("OrderCreator-2");
        Thread orderToEsbSender = new Thread(new OrderToEsbSender(orderQueue));
        orderCreator.start();
        orderCreator2.start();
        orderToEsbSender.start();
        orderToEsbSender.setName("OrderToEsbSender-1");
    }

    static class OrderCreator implements Runnable{
        private final Queue<CustomerOrder> orderQueue;
        private Long orderId = 0L;
        public OrderCreator(Queue<CustomerOrder> orderQueue) {
            this.orderQueue = orderQueue;
        }

        @Override
        public void run() {
            while(true){
                try {
                    lock.lock();
                    createOrder();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }

        private void createOrder() throws InterruptedException{
            if(orderQueue.size()==25){
                System.out.println(Thread.currentThread().getName()+" : Size limit wait");
                condition.await();
            }
            Thread.sleep(1000);
            orderId++;
            System.out.println(Thread.currentThread().getName()+" : "+orderId+ " id ile order oluşturuluyor");
            CustomerOrder order = new CustomerOrder();
            order.setCustomerOrderId(orderId);
            orderQueue.add(order);
            if(orderQueue.size() == 1){
                System.out.println(Thread.currentThread().getName()+" : signal");
                condition.signal();
            }
        }
    }

    static class OrderToEsbSender implements Runnable{
        private final Queue<CustomerOrder> orderQueue;

        public OrderToEsbSender(Queue<CustomerOrder> orderQueue) {
            this.orderQueue = orderQueue;
        }

        @Override
        public void run() {
            while(true){
                try {
                    lock.lock();
                    sendOrderToEsb();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    lock.unlock();
                }
            }
        }

        private void sendOrderToEsb() throws InterruptedException{
            if (orderQueue.isEmpty()){
                System.out.println(Thread.currentThread().getName()+": Queue empty wait");
                condition.await();
            }

            Thread.sleep(500);

            CustomerOrder order = orderQueue.poll();
            System.out.println(Thread.currentThread().getName()+" : Order " +order.getCustomerOrderId() +" sent to esb");

            if(orderQueue.size()==24) {
                System.out.println(Thread.currentThread().getName()+" : signal");
                condition.signal();
            }
        }
    }
}
