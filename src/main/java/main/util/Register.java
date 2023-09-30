package main.util;

/**
 * A class to emulate one cpu register
 */
public class Register {
    /**
     * The value of the register, should be a {@link Register#size} sized bitvalue
     */
    private int value = 0;
    /**
     * The size aka amount of bits of the register
     */
    public final int size;
    /**
     * A mask with {@link Register#size} amount of bits used for calculations
     */
    private final int mask;

    /**
     * Get one bit from the register
     * @param bit The number of bit to get (0-{@link Register#size})
     * @return The value of the bit as a boolean
     * @throws RegisterException If a bit was tried to access, which doesn't exist in the register
     */
    public boolean getBit(int bit) {
        if(bit>=size){
            throw new RegisterException("Tried to access nonexistent bit!");
        }
        int tempregister = value;
        tempregister >>= bit;
        tempregister &= 1;
        return tempregister > 0;
    }

    /**
     * Set a single bit of the register
     * @param bit Which bit to set
     * @param value Which value to set the bit to
     * @throws RegisterException If a bit was tried to access, which doesn't exist in the register
     */
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

    /**
     * Create a register with a specific size
     * @param size The size aka amount of bits for the register
     */
    public Register(int size){
        this.size = size;
        this.mask = (int)Math.pow(2,size)-1;
    }

    /**
     * Create a default 8-bit register, for different sizes use {@link Register#Register(int size)}
     */
    public Register(){
        this(8);
    }

    /**
     * Gets the value of the register as an int
     * @return The value of the register as an int
     */
    public int getValue(){
        return value;
    }

    /**
     * Set the value of the register to the given int, bits that don't fit are simply cut off
     * @param value The value to set the register to
     */
    public void setValue(int value){
        this.value = value & this.mask;
    }

    /**
     * Turn the register into a readable form, most significant bit to the left, least significant bit to the right
     * @return The registers bits in square brackets
     */
    @Override
    public String toString() {//TODO Increase performance maybe
        String output = "[";
        for(int i = size; i > 0; i--){
            output += String.valueOf(getBit(i-1)? 1:0);
        }
        output += "]";
        return output;
    }

    /**
     * gets 2 Registers as one bigger register
     * @param r1 More significant bits
     * @param r2 Less significant bits
     * @return The value of both registers combined
     */
    public static int get2RegistersAsValue(Register r1, Register r2){
        int value1 = r1.getValue();
        int value2 = r2.getValue();
        return value2+(value1<<r2.size);
    }

    /**
     * A class for Exceptions in the Register
     */
    public static class RegisterException extends RuntimeException{
        /**
         * Something went wrong with the registers
         * @param error Error message
         */
        public RegisterException(String error){
            super(error);
        }
    }
}


