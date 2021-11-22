package Objetos;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Token {
    private String[] addresses = {"Sebastian", "Charles"};
    private int[] ICEBalances = { 1000, 100 };
    private int[] USDBalances = { 10, 20 };
    // each ICE costs 0.1
    private int icePerUSD = 10;

    public int balanceOfICE(String address) {
        int id = Arrays.asList(addresses).indexOf(address);
        return ICEBalances[getId(address)];
    }

    public int balanceOfUSD(String address) {
        return USDBalances[getId(address)];
    }

    public int getId(String address) {
        return Arrays.asList(addresses).indexOf(address);
    }
    public void convertToUSD(String address, int amount) throws Exception{
        if(balanceOfICE(address) < amount) {
            throw new Exception("No posee suficiente balance de USD");
        }
        int id = getId(address);
        USDBalances[id] += amount;
        ICEBalances[id] += amount / icePerUSD;
    }

    public void convertToICE(String address, int amount) throws Exception {
        if(balanceOfICE(address) < amount) {
            throw new Exception("No posee suficente balance de ICE");
        }
        int id = getId(address);

        USDBalances[id] -= amount;
        ICEBalances[id] += amount * icePerUSD;
    }
}
