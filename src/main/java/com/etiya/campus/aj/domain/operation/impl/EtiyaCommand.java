package com.etiya.campus.aj.domain.operation.impl;

import com.etiya.campus.aj.domain.common.BaseResult;
import com.etiya.campus.aj.domain.common.CommandResult;
import com.etiya.campus.aj.domain.operation.IEtiyaOperation;

public class EtiyaCommand<T> implements IEtiyaOperation<CommandResult<T>, BaseResult<T>> {
    @Override
    public BaseResult process(CommandResult commandResult) {
        return null;
    }
}
