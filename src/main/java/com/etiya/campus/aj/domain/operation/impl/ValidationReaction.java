package com.etiya.campus.aj.domain.operation.impl;

import com.etiya.campus.aj.domain.common.BaseResult;
import com.etiya.campus.aj.domain.common.ValidationResult;
import com.etiya.campus.aj.domain.operation.IEtiyaOperation;

public class ValidationReaction implements IEtiyaOperation<ValidationResult, BaseResult> {

    @Override
    public BaseResult process(ValidationResult validationResult) {
        return null;
    }
}
