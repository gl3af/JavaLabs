package com.example.beans_jsp;

public class MainBean {
    private int redirectsCount = 0;
    private int oddSum = 0;
    private int evenSum = 0;

    public MainBean() {
    }

    public void executeTask(String data) {
        oddSum = 0;
        evenSum = 0;
        String[] values = data.split(" ");
        for (String value : values) {
            int number = Integer.parseInt(value);
            if (number % 2 == 0) evenSum += number;
            else oddSum += number;
        }
    }

    public int getRedirectsCount() {
        return redirectsCount;
    }

    public void setRedirectsCount(int redirectsCount) {
        this.redirectsCount = redirectsCount;
    }

    public int getOddSum() {
        return oddSum;
    }

    public void setOddSum(int oddSum) {
        this.oddSum = oddSum;
    }

    public void setEvenSum(int evenSum) {
        this.evenSum = evenSum;
    }

    public int getEvenSum() {
        return evenSum;
    }
}
