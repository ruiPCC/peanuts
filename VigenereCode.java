import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 13/11/2018.
 *
 */
public class VigenereCode {
    private String key;
    private char[] keyInArray;
    private Map<Integer,Character> intToCharMap;
    private Map<Character,Integer> charToIntMap;


    /*
    Constructor method, (preferably the key should not has unusual characters)
     */
    public VigenereCode(String key) {

        this.key = key;
        this.keyInArray = preparateStringToCode(key).toCharArray();
        intToCharMap = buildOrderToLetter();
        charToIntMap = buildLetterToOrder();
    }


    /*
    Method that codes a message (preferably with no unusual characters)
     */
    public String coding(String msg) {
        char[] msgInArray = preparateStringToCode(msg).toCharArray();
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
        return intToCharMap.get((charToIntMap.get(c)+charToIntMap.get(keyChar))%Alphabet.values().length);
    }

    /*
    Method that decodes a message (preferably with no unusual characters)
     */
    public String decoding(String msg) {
        char[] msgInArray = preparateStringToCode(msg).toCharArray();
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
        return intToCharMap.get((charToIntMap.get(c)+Alphabet.values().length-charToIntMap.get(keyChar))%Alphabet.values().length);
    }


    /*
    Method that generate a map that gives the order (in alphabet) of a letter of the alphabet
     */
    private Map<Integer,Character> buildOrderToLetter() {
        Map<Integer,Character> output = new HashMap<Integer, Character>();
        for (Alphabet letter: Alphabet.values()) {
            output.put(letter.getOrder(),letter.getLetter());
        }
        return output;
    }

    /*
    Method that generate a map that gives the letter of the alphabet according  to its order in alphabet
     */
    private Map<Character,Integer> buildLetterToOrder() {
        Map<Character,Integer> output = new HashMap<Character, Integer>();
        for (Alphabet letter: Alphabet.values()) {
            output.put(letter.getLetter(),letter.getOrder());
        }
        return output;
    }

    /*
    Method to transform a string to a codable string (with caps, spaces, pontuation and acentuation)
     */
    private String preparateStringToCode(String string) {
        return string.toLowerCase().replaceAll("[àáã]","a").replaceAll("[èéê]","e")
                .replaceAll("[ìí]","i").replaceAll("[óòõô]", "o").replaceAll("[úù]","u")
                .replaceAll("[ç]","c").replaceAll("[^a-z]","");
    }

}
