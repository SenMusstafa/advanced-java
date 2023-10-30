package com.etiya.campus.aj.domain.validation;

import com.etiya.campus.aj.domain.characteristic.ProductChar;
import com.etiya.campus.aj.domain.common.ValidationResult;

import java.util.List;

public interface IValidator {
    List<ValidationResult> getResults();
    void validateAll();
}
