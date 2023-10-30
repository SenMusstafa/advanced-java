package com.etiya.campus.aj.domain.common;

import java.util.Objects;

public class BaseResult<T> {
    private int resultCode;
    private String resultMessage;
    private T result;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResult<?> that = (BaseResult<?>) o;
        return resultCode == that.resultCode && Objects.equals(resultMessage, that.resultMessage) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, resultMessage, result);
    }
}
