package model;

import dto.StatementDto;
import service.impl.StatementServiceImpl;
import utils.funcInterfaces.FiveFunction;

public enum StatementType {
    MONEY_FLOW(StatementServiceImpl::buildMoneyFlowStatement),
    TRANSACTIONS_HISTORY(StatementServiceImpl::buildTransactionHistoryStatement);

    public final FiveFunction<StatementServiceImpl, StatementDto, Account, User, Bank, String> statementAlgo;

    StatementType(FiveFunction<StatementServiceImpl, StatementDto, Account, User, Bank, String> statementAlgo) {
        this.statementAlgo = statementAlgo;
    }
}
