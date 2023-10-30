package com.etiya.campus.aj.domain.common;

import com.etiya.campus.aj.domain.validation.IValidationAction;
import com.etiya.campus.aj.domain.validation.IValidator;

import java.util.List;

public class ValidationResult extends BaseResult{
    private IValidator source;
    private List<IValidationAction> actions;

    public IValidator getSource() {
        return source;
    }

    public void setSource(IValidator source) {
        this.source = source;
    }

    public List<IValidationAction> getActions() {
        return actions;
    }

    public void setActions(List<IValidationAction> actions) {
        this.actions = actions;
    }
}
