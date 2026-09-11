package dto;

import enums.ATMStatus;

public class Atm {
    private String id;
    private double cashAvailable;
    private int twoThousandCount;
    private int fiveHundredCount;
    private int oneHundredCount;

    public ATMStatus getStatus() {
        return status;
    }

    public void setStatus(ATMStatus status) {
        this.status = status;
    }

    private ATMStatus status;

    public Atm(String id, double cashAvailable, int twoThousandCount, int fiveHundredCount, int oneHundredCount) {
        this.id = id;
        this.cashAvailable = cashAvailable;
        this.twoThousandCount = twoThousandCount;
        this.fiveHundredCount = fiveHundredCount;
        this.oneHundredCount = oneHundredCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCashAvailable() {
        return cashAvailable;
    }

    public void setCashAvailable(double cashAvailable) {
        this.cashAvailable = cashAvailable;
    }

    public int getTwoThousandCount() {
        return twoThousandCount;
    }

    public void setTwoThousandCount(int twoThousandCount) {
        this.twoThousandCount = twoThousandCount;
    }

    public int getFiveHundredCount() {
        return fiveHundredCount;
    }

    public void setFiveHundredCount(int fiveHundredCount) {
        this.fiveHundredCount = fiveHundredCount;
    }

    public int getOneHundredCount() {
        return oneHundredCount;
    }

    public void setOneHundredCount(int oneHundredCount) {
        this.oneHundredCount = oneHundredCount;
    }
}
