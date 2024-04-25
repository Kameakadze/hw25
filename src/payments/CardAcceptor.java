package payments;

import exceptions.CatchPaymentException;

import java.util.Scanner;

public class CardAcceptor implements MoneyPayment {
    private int amount;
    private static int balance = 500;
    private String numberOfCard;
    private String password;

    public CardAcceptor(int amount) {
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
        boolean isRun = true;
        while (isRun) {
            try {
                if (numberOfCard == null) {
                    String number = tryParseToNumber("Введите номер карты (16 цифр).");
                    if (number.length() != 16) {
                        throw new CatchPaymentException("Введено не верное количество символов, повторите еще раз.");
                    }
                    numberOfCard = number;
                } else if (password == null) {
                    String onTimePassword = tryParseToNumber("Введите пароль.");
                    if (onTimePassword.length() != 4) {
                        throw new CatchPaymentException("Введен не верный пароль.");
                    }
                    password = onTimePassword;
                } else {
                    int sum = parseInt("Введите сумму пополнения. " +
                            "\nДоступная сумма на балансе карты: " + balance);
                    if (sum <= balance) {
                        amount += sum;
                        balance -= sum;
                        isRun = false;
                    } else {
                        throw new CatchPaymentException("Недостаточно денег на балансе.");
                    }
                }
            } catch (NumberFormatException | CatchPaymentException e) {
                e.printStackTrace();
                System.err.println("Введены неверные данные. " + e.getMessage());
            }
        }
    }

    public static String inputFromConsole(String text) {
        while (true) {
            System.out.println(text);
            String str = new Scanner(System.in).nextLine();
            if (str.isEmpty() || str.isBlank()) {
                System.err.println("Не корректно введены данные.");
            } else {
                return str;
            }
        }
    }
    public static String tryParseToNumber(String text) {
        while (true) {
            String resultText = inputFromConsole(text);
            try {
                long result = Long.parseLong(resultText);
                if (result < 0) {
                    throw new CatchPaymentException("Пароль должен содержать только цифры.");
                }
                return resultText;
            } catch (NumberFormatException | CatchPaymentException e) {
                e.printStackTrace();
                System.err.println("Введены неверные данные.");
            }
        }
    }

    public static int parseInt(String text) {
        while (true) {
            String resultText = inputFromConsole(text);
            try {
                int result = Integer.parseInt(resultText);
                if (result < 0) {
                    throw new CatchPaymentException("Введенное значение не должно быть меньше 0.");
                }
                return result;
            } catch (NumberFormatException | CatchPaymentException e) {
                e.printStackTrace();
                System.err.println("Введены неверные данные.");
            }
        }
    }
}