package com.etiya.campus.aj.domain.validation;

import java.util.List;

public class GenericValidator {
    public void valdiateAll(List<? extends IValidator> validatorList){
        validatorList.forEach(IValidator::validateAll);
    }

    public String toLoweCase(String s){
        return s.toLowerCase();
    }
}
