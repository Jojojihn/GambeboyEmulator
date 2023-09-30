package main.util;

public class Register {
    private int value = 0;
    private final int size;
    private final int mask;

    public boolean getBit(int bit) {
        if(bit>=size){
            throw new RegisterException("Tried to access nonexistent bit!");
        }
        int tempregister = value;
        tempregister >>= bit;
        tempregister &= 1;
        return tempregister > 0;
    }

    public void setBit(int bit, boolean value){
        if(bit>=size){
            throw new RegisterException("Tried to access nonexistent bit!");
        }
        int tempvalue = value ? 1 : 0;
        int clearer = 1;
        clearer <<= bit;
        clearer = ~clearer;
        this.value = this.value & clearer;
        tempvalue <<= bit;
        this.value |= tempvalue;

    }

    public Register(int size){
        this.size = size;
        this.mask = (int)Math.pow(2,size)-1;
    }

    public Register(){
        this(8);
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value & this.mask;
    }

    @Override
    public String toString() {//TODO Increase performance maybe
        String output = "[";
        for(int i = size; i > 0; i--){
            output += String.valueOf(getBit(i-1)? 1:0);
        }
        output += "]";
        return output;
    }
    public static class RegisterException extends RuntimeException{
        public RegisterException(String error){
            super(error);
        }
    }
}


