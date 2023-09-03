package model;

import lombok.Getter;
/**
 *  �����, �������������� ����� ������������ ����� ����������
 */
@Getter
public enum TransactionType {
    DEPOSIT("����������"),
    WITHDRAW("������"),
    TRANSFER("��������");

    private final String action;

    TransactionType(String action) {
        this.action = action;
    }

}