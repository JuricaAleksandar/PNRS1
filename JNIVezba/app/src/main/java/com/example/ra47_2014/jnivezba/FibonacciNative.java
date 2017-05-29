package com.example.ra47_2014.jnivezba;

/**
 * Created by student on 29.5.2017.
 */

public class FibonacciNative {

    public native int get(int n);

    static {
        System.loadLibrary("fibonacci");
    }
}
