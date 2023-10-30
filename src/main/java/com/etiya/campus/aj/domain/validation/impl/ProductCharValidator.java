package com.etiya.campus.aj.domain.validation.impl;

import com.etiya.campus.aj.domain.characteristic.ProductChar;
import com.etiya.campus.aj.domain.common.ValidationResult;
import com.etiya.campus.aj.domain.validation.IValidator;

import java.util.ArrayList;
import java.util.List;

public class ProductCharValidator implements IValidator {
    private List<ProductChar> productCharList = new ArrayList<>();

    @Override
    public List<ValidationResult> getResults() {
        return null;
    }

    @Override
    public void validateAll() {
        System.out.println("VALIDATED");
    }
}
