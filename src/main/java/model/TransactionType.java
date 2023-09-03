package model;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT("Пополнение"),
    WITHDRAW("Снятие"),
    TRANSFER("Трансфер");

    private final String action;

    TransactionType(String action) {
        this.action = action;
    }


}
