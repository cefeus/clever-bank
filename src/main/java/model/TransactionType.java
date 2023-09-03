package model;

import lombok.Getter;
/**
 *  Класс, представляющий собой перечисление типов транзакций
 */
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