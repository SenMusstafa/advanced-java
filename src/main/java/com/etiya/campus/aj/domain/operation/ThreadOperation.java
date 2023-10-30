package com.etiya.campus.aj.domain.operation;

public class ThreadOperation<T,R> {
    private T input;
    private R result;

    public boolean processOperation(){
        return true;
    }

    public T getInput() {
        return input;
    }

    public void setInput(T input) {
        this.input = input;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }
}
