package com.etiya.campus.aj.domain.common;


import com.etiya.campus.aj.domain.operation.impl.EtiyaCommand;

import java.util.ArrayList;
import java.util.List;

public class  CommandResult<T> extends BaseResult<T> {
    EtiyaCommand<T> source;
    List<String> effectedItemKeys;

    public List<String> getEffectedItemKeys() {
        return effectedItemKeys;
    }

    public void setEffectedItemKeys(List<String> effectedItemKeys) {
        this.effectedItemKeys = effectedItemKeys;
    }

    public EtiyaCommand<T> getSource() {
        return source;
    }

    public void setSource(EtiyaCommand<T> source) {
        this.source = source;
    }
}
