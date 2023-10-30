package com.etiya.campus.aj.domain.operation;

public interface IEtiyaOperation <T,R>{
    R process(T t);
}
