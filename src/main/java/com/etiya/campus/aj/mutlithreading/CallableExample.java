package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.Callable;

public class CallableExample implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2500);
        return 1;
    }
}
