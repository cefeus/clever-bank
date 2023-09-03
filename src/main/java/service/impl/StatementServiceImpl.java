package service.impl;

import dto.StatementDto;
import repository.AccountRepo;
import repository.BankRepo;
import repository.impl.AccountRepoImpl;
import repository.impl.BankRepoImpl;
import service.StatementService;

public class StatementServiceImpl implements StatementService {

    private AccountRepo accRepo = new AccountRepoImpl();
    private BankRepo bankRepo = new BankRepoImpl();
    private UserRepo userRepo = new UserRepoImpl();
    @Override
    public void createTransactionStatement(StatementDto statementDto) {
        //validation

    }
}
