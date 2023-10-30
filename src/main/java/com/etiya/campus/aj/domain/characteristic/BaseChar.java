package com.etiya.campus.aj.domain.characteristic;

import com.etiya.campus.aj.domain.validation.IValidatable;
import com.etiya.campus.aj.domain.validation.IValidator;

import java.util.Objects;

public class BaseChar<P,V extends IValidator> implements IValidatable {
    private String shrtCode;
    private Long id;
    private String val;
    private P provider;
    private V validator;
    public String getShrtCode() {
        return shrtCode;
    }

    public void setShrtCode(String shrtCode) {
        this.shrtCode = shrtCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String getValidationKey() {
        return this.shrtCode;
    }

    @Override
    public String getValidationValue() {
        return this.val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseChar baseChar = (BaseChar) o;
        return Objects.equals(shrtCode, baseChar.shrtCode) && Objects.equals(val, baseChar.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shrtCode, val);
    }
}
