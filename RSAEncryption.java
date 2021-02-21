import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class RSAEncryption {

	private static List<Integer> primeNumbers = new ArrayList<Integer>();

	private static int primeNumberPublic = 0;
	
	private static int primeNumberPublic2 = 0;
	
	private static int primeNumberPrivate = 0;
	
	private static int primeNumberPrivate2 = 0;
	
	private static int publicKey = 0;
	
	private static BigInteger privateKey;
	
	private static final int e = 11; 
	
	private static FileWriter fileWriter;


	private static final void GeneratePublicKey() {
		
		int range = primeNumbers.size();
		
		int randomNumber1 = (int)(Math.random() * range); 
			
		int randomNumber2 = (int)(Math.random() * range); 
		
		primeNumberPublic = primeNumbers.get(randomNumber1);
		primeNumberPublic2 = primeNumbers.get(randomNumber2);
		
		System.out.println("The first prime number is " + primeNumberPublic);
		System.out.println("The second prime number is " + primeNumberPublic2);
		
		publicKey =  primeNumberPublic * primeNumberPublic2;
		
		System.out.println("The final public key is " + publicKey);
	}
	
	private static int FindLCM(int number1, int number2) {
		
		int lcm = 0;
		
		if (number1 > number2) {
			lcm = number1;
		} else {
			lcm = number2;
		}
		
		while (true) {
			if (lcm % number1 == 0 && lcm % number2 == 0) {
				break;
			}
			lcm = lcm + 1;
		}
		
		return lcm;
	}
	
	private static void GeneratePrivateKey() {
		
		int totient = FindLCM((primeNumberPublic - 1),(primeNumberPublic2 - 1));
		
		System.out.println(totient);
		
		BigInteger convertedE = BigInteger.valueOf(e);
		BigInteger convertedTotient = BigInteger.valueOf(totient);
				
		privateKey = convertedE.modInverse(convertedTotient);
	}
	
	private static final void WritePublicAndPrivateKey() {
		
		try {
		fileWriter = new FileWriter("PublicPrivateKey.txt");
		
		fileWriter.write(Double.toString(publicKey));
		fileWriter.write("\n");
		
		fileWriter.write(String.valueOf(privateKey));
		fileWriter.write("\n");
		
		fileWriter.flush();
		fileWriter.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static final void InitializePrimeNumbers() {
		primeNumbers.add(1009);
		primeNumbers.add(1013);
		primeNumbers.add(1019);
		primeNumbers.add(1021);
		primeNumbers.add(1031);
		primeNumbers.add(1033);
		primeNumbers.add(1039);
		primeNumbers.add(1049);
		primeNumbers.add(1051);
		primeNumbers.add(1061);
		primeNumbers.add(1063);
		primeNumbers.add(1069);
		primeNumbers.add(1723);
		primeNumbers.add(1787);
		primeNumbers.add(1663);
		primeNumbers.add(1381);
	}
	
	public static void main(String[] arguments) {
		InitializePrimeNumbers();
		GeneratePublicKey();
		GeneratePrivateKey();
		WritePublicAndPrivateKey();
	}


}