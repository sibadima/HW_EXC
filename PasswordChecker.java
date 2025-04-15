public class PasswordChecker {

    private int minLength = -1;
    private int maxRepeats = -1;

    public void setMinLength(int minLength) { //сеттер минимально допустимой длинны пароля
        if (minLength < 0) {
            throw new IllegalArgumentException("Ошибка! Длина пароля не может быть отрицательной");
        }
        this.minLength = minLength;

    }

    public void setMaxRepeats(int maxRepeats) { //сеттер установки максимально допустимого количества повторений символа подряд
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException("Ошибка. Количество повторений должно быть больше 0");

        }
        this.maxRepeats = maxRepeats;

    }

    public boolean verify(String password) { //метод верификации пароля на 2 исключения setMinLength и setMaxRepeats
        if (minLength == -1 || maxRepeats == -1) { //проверяем
            throw new IllegalStateException("Чеккер не настроен.");
        }

        if (password.length() < minLength) { //проверка длины пароля с минимальной длиной пароля согласно условия
            return false;
        }

        //проверка пароля на максимально допустимое количество повторений символа подряд
        int counter = 0;
        char previousLetter = password.charAt(0); //берем первую букву пароля
        for (char letter : password.toCharArray()) { //перебираем все буквы пароля
            if (letter == previousLetter) {
                counter++;
            } else {
                counter = 1;
            }
            if (counter > maxRepeats) {
                return false; //пароль не подходит по критериям
            }
            previousLetter = letter;
        }
        return true;
    }
}
