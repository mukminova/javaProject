package ru.innopolis;

public class KillThreadStatus {
    private boolean isAlive;//переменная для остановки всех потоков

    public KillThreadStatus(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
