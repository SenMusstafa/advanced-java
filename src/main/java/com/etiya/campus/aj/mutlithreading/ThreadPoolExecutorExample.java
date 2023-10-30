package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //example1();
        //example2();
        //example3();
        example4();
    }

    private static void example1() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3)
                //new LinkedBlockingDeque<>()
                //new SynchronousQueue<>()// direk taskı threade atar tutmaz
        );

        //threadPoolExecutor.prestartAllCoreThreads();

        threadPoolExecutor.execute(() -> System.out.println("Task 1"));
        threadPoolExecutor.execute(() -> System.out.println("Task 2"));

        System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(3, TimeUnit.SECONDS);
    }

    private static void example2() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3)
        );

        System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());

        Future<Double> future = threadPoolExecutor.submit(new CallableTask());

        System.out.println(future.get());

        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(3, TimeUnit.SECONDS);
    }

    private static void example3() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,
                3,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                //(Runnable r, ThreadPoolExecutor executor)->{System.out.println("Task rejected");}
                new ThreadPoolExecutor.DiscardPolicy()
        );

        threadPoolExecutor.submit(new SleepTask(1));
        threadPoolExecutor.submit(new SleepTask(2));

        System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.submit(new SleepTask(3));
        threadPoolExecutor.submit(new SleepTask(4));

        threadPoolExecutor.submit(new SleepTask(5));
        System.out.println("Pool size: " + threadPoolExecutor.getPoolSize());

        threadPoolExecutor.submit(new SleepTask(6));
    }

    private static void example4() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3)
        );
        threadPoolExecutor.getPoolSize();
        threadPoolExecutor.getActiveCount();
        threadPoolExecutor.getCompletedTaskCount();

    }


    static class CallableTask implements Callable<Double> {

        @Override
        public Double call() throws Exception {
            return Math.random();
        }
    }

    static class SleepTask implements Runnable {
        private final int id;

        public SleepTask(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
