import java.io.*;
import java.util.*;

import java.math.BigInteger;

public class EncryptAndDecrypt {
		
	private static Scanner fileScanner;
	
	private static final File file = new File("PublicPrivateKey.txt");
	
	private static final int e = 11; 
	
	private static int PublicKey = 0; 
	
	private static int PrivateKey = 0;
	
	private static HashMap <Character, Integer> letterToString = new HashMap<Character, Integer>(); 
	
	private static BigInteger encryptedResult;
	
	private static void ReceiveKeys() {
		
		try {
			fileScanner = new Scanner(file);
			
			String draftPublicKey = fileScanner.nextLine(); //Doesn't let me do integer conversions from .nextLine method
		
			String draftPrivateKey = fileScanner.nextLine();
		
			PublicKey = (int)Double.parseDouble(draftPublicKey);
		
			PrivateKey = (int)Double.parseDouble(draftPrivateKey);
			
			System.out.println(PublicKey);
			
			System.out.println(PrivateKey);
				
		} 	catch(FileNotFoundException f) { }
				
	}

	
	private static void EncryptString(String value) {	
		String convertedInput = "";
		
		char[] charArray = value.toCharArray();
		
		for (int i = 0; i < value.length(); i = i + 1) {
			convertedInput = convertedInput + letterToString.get(charArray[i]);
		}
		
		System.out.println("The converted string is " + convertedInput);	
		
		BigInteger convertedInputString = new BigInteger(convertedInput); 
		
		encryptedResult = convertedInputString.modPow(BigInteger.valueOf(e), BigInteger.valueOf(PublicKey));
		
		System.out.println("The final encrypted result is " + encryptedResult);
	}
	
	private static void DecryptString(BigInteger encrypted) {	
		BigInteger decryptedResult = encrypted.modPow(BigInteger.valueOf(PrivateKey), BigInteger.valueOf(PublicKey));
		
		String stringResult = String.valueOf(decryptedResult);
				
		String finalResult = "";
		
		for (int i = 0; i < stringResult.length(); i = i + 1) {
			finalResult = finalResult + getKeyFromValue(letterToString, Integer.valueOf(stringResult.substring(i, i + 1)));
		}

		System.out.println("The final decrypted result is " + finalResult);	
	}
	
	private static Object getKeyFromValue(Map map, Object value) { //Code snippet taken from https://stackoverflow.com/questions/8112975/get-key-from-a-hashmap-using-the-value
    for (Object o : map.keySet()) {
      if (map.get(o).equals(value)) {
        return o;
      }
    }
    return null;
  }
	
	public static final void InitializeLookupTable() {
        letterToString.put('A', 1);
        letterToString.put('B', 2);
        letterToString.put('C', 3);
        letterToString.put('D', 4);
        letterToString.put('E', 5);
        letterToString.put('F', 6);
        letterToString.put('G', 7);
        letterToString.put('H', 8);
        letterToString.put('I', 9);
        letterToString.put('J', 10);
        letterToString.put('K', 11);
        letterToString.put('L', 12);
        letterToString.put('M', 13);
        letterToString.put('N', 14);
        letterToString.put('O', 15);
        letterToString.put('P', 16);
        letterToString.put('Q', 17);
        letterToString.put('R', 18);
        letterToString.put('S', 19);
        letterToString.put('T', 20);
        letterToString.put('U', 21);
        letterToString.put('V', 22);
        letterToString.put('W', 23);
        letterToString.put('X', 24);
        letterToString.put('Y', 25);
        letterToString.put('Z', 26);
    }
	
	/**
		Some improvements which need to be made are:
		1. Add a timer and see how much time it takes to encrypt a string
		2. Fix the bug which doesn't allow 7+ digit numbers to be encrypted
		3. Fix the error on line 70, which can only take in 1 number. Use a list to add the numbers and then encrypt from there.
	
	**/
	
	public static void main(String[] arguments) {
		InitializeLookupTable();
		ReceiveKeys();
		EncryptString("HIHIHI");
		DecryptString(encryptedResult);
	}
}