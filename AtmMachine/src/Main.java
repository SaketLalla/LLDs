import dto.Account;
import dto.Atm;
import dto.Card;
import enums.ATMStatus;
import service.AtmService;

void main() {
    //Create Objects
    //1. Card
    Card card = new Card(
            "1",
            "CARD123",
            "1234",
            new Account("123","Saket", "AB12",10000.99)
    );

    Atm atm1 = new Atm("ATM1",14500, 5, 5, 20);
    atm1.setStatus(ATMStatus.IDLE);
    Atm atm2 = new Atm("ATM2",1500 ,0, 2, 5);

    AtmService atmService = new AtmService(atm1);
    System.out.println("=========== ATM MACHINE ================");

    System.out.println("ATM Being used is "+ atm1.getId());
    //Perform Actions
    //1. Insert the Card
    atmService.insertCard(card);
    //2. Enter the PIN
    atmService.enterPin("1234");
    //3.Select Cash WithDraw Option
    atmService.selectOption("CASH_WITHDRAW"); //Other options can be DEPOSIT , CHECK_BALANCE
    //4. Take Out the Cash
    atmService.dispenseCash(6400);

    System.out.println(card.getAccount().getName() +" has "+card.getAccount().getBalance() +" balance left");
}
