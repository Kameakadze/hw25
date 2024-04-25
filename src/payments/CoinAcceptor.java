package payments;

public class CoinAcceptor implements MoneyPayment {
    private int amount;

    public CoinAcceptor(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addMoney() {
        System.out.println("Торговый автомат принимает только монеты, номиналом 10.");
        amount += 10;
        System.out.println("Произошло пополнение на 10 сом.");
    }
}
