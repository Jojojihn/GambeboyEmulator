import main.util.Register;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static main.util.Register.get2RegistersAsValue;
import static org.junit.Assert.*;

/**
 * Class to test the registers for errors
 */
public class TestRegisterUnit {

    /**
     * Test to see if testing framework is working properly
     */
    @Test
    public void TestTests(){
        assertEquals("Something went really really wrong with the testing framework", "Zain", "Zain");
    }

    /**
     * Test if creating an empty register works
     */
    @Test
    public void TestEmptyRegister(){
        Register r = new Register();
        assertEquals("Something went wrong, the Registers aren't empty anymore!","[00000000]", r.toString());
    }

    /**
     *Test if setting a single bit works
     */
    @ParameterizedTest
    @CsvSource({"0,[00000001]","1,[00000010]","2,[00000100]","3,[00001000]","4,[00010000]","5,[00100000]","6,[01000000]","7,[10000000]"})
    public void Test1BitSetting(int bit,String result){
        Register r = new Register();
        r.setBit(bit, true);
        assertEquals("Register Bit setting is wrong",r.toString(), result);
    }

    /**
     * Test if setting multiple bits in the same register works
     */
    @ParameterizedTest
    @CsvSource({"0,2,[00000101]","1,2,[00000110]","2,7,[10000100]","3,5,[00101000]","4,6,[01010000]","5,7,[10100000]","6,1,[01000010]","7,0,[10000001]"})
    public void Test2BitSetting(int bit1, int bit2,String result){
        Register r = new Register();
        r.setBit(bit1, true);
        r.setBit(bit2, true);
        assertEquals("Register Bit setting is wrong",r.toString(), result);
    }

    /**
     * Test to see if getting bits works
     */
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6,7})
    public void TestBitGettingSelf(int bit){
        Register r = new Register();
        r.setBit(bit, true);
        assertEquals("Bit getting, doesnt work",r.getBit(bit),true);
    }

    /**
     * Test to see if returning the value of an 8bit register works
     */
    @ParameterizedTest
    @CsvSource({"0,0","255,255","69,69","300,44"})
    public void TestValueReturn8bit(int value, int expected){
        Register r = new Register();
        r.setValue(value);
        assertEquals("Something went wrong with getting/setting the value. (8bit)",expected, r.getValue());
    }

    /**
     * Test to see if returning the value of a 16bit register works
     */
    @ParameterizedTest
    @CsvSource({"0,0","255,255","69,69","300,300","0xffff,0xffff"})
    public void TestValueReturn16bit(int value, int expected){
        Register r = new Register(16);
        r.setValue(value);
        assertEquals("Something went wrong with getting/setting the value. (8bit)",expected, r.getValue());
    }

    /**
     * Test to see if reading two registers works as intended
     */
    @Test
    public void TestGetTwoRegisterReading(){
        Register lsb = new Register();
        Register msb = new Register();
        lsb.setValue(69);
        msb.setValue(69);
        int result = get2RegistersAsValue(msb,lsb);
        assertEquals(17733,result);
    }

}
