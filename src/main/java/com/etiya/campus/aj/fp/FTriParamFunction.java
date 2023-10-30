package com.etiya.campus.aj.fp;
@FunctionalInterface
public interface FTriParamFunction<X,Y,Z, R> {
    public abstract R apply(X p1,  Y p2, Z p3);
}
