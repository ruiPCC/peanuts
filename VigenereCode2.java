/**
 * Created by codecadet on 19/11/2018.
 */
public class VigenereCode2 {

    private String key;
    private char[] keyInArray;
    private static final int rangeOfChar = 999;

    public VigenereCode2(String key) {
        this.key = key;
        keyInArray = key.toCharArray();
    }


    /*
    Method that codes a message (preferably with no unusual characters)
     */
    public String coding(String msg) {
        char[] msgInArray = msg.toCharArray();
        String output = "";
        for (int i = 0; i < msgInArray.length; i++) {
            msgInArray[i] = codifyChar(i,msgInArray[i]);
            output += msgInArray[i];
        }
        return output;
    }

    /*
    Method that codes a single character
     */
    private char codifyChar(int i, char c) {
        char keyChar = keyInArray[i%keyInArray.length];
        return ((char) ((((int) c)+((int) keyChar))% rangeOfChar));
    }

    /*
    Method that decodes a message (preferably with no unusual characters)
     */
    public String decoding(String msg) {
        char[] msgInArray = msg.toCharArray();
        String output = "";
        for (int i = 0; i < msgInArray.length; i++) {
            msgInArray[i] = decodifyChar(i,msgInArray[i]);
            output += msgInArray[i];
        }
        return output;
    }

    /*
    Method that codes a single character
     */
    private char decodifyChar(int i, char c) {
        char keyChar = keyInArray[i%keyInArray.length];
        return ((char) ((((int) c) + rangeOfChar - ((int) keyChar)) % rangeOfChar));
    }

}
