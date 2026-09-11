package dto;

public class Card {
    String id;
    String cardNumber;
    String pin;
    Account account;

    public Card(String id, String cardNumber, String pin, Account account) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
