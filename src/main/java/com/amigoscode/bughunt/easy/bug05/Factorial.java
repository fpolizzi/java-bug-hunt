package com.amigoscode.bughunt.easy.bug05;

public class Factorial {

    public long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
