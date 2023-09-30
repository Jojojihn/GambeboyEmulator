package main.gameboy;
import main.util.Register;

public class CPU {
    private Register accumulator;
    private Register flags;
    private Register b;
    private Register c;
    private Register d;
    private Register e;
    private Register h;
    private Register l;

    /**
     * gets 2 Registers as one bigger register
     * @param r1 More significant bits
     * @param r2 Less significant bits
     * @return The value of both registers combined
     */
    private int get2Registers(Register r1, Register r2){
        int value1 = r1.getValue();
        int value2 = r2.getValue();
        return value2+(value1<<r2.size);
    }



}
