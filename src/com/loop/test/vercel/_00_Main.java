package com.loop.test.vercel;

public class _00_Main {

    public static void main(String[] args) throws InterruptedException {

        _0_Home.run();

        System.out.println();
        System.err.println("No A/B Test test");
        _1_NoABTest.run();

        System.out.println();
        System.err.println("Add/Remove Element test");
        _2_AddRemoveElements.run();

        System.out.println();
        System.err.println("Autocomplete test");
        _3_Autocomplete.run();
    }
}
