package cop2805;

public class Encrypt {
	public static String Decrypter(String stringy) {
		int i;
		String coded = "";
		char index;
		
		for (i = 0; i < stringy.length(); i++) {
			index = (char) (stringy.charAt(i) + 10);
			coded += index;
		}
		
		return coded;
	}
	
	public static String Encrypter(String stringy) {
		int i;
		String coded = "";
		char index;
		
		for (i = 0; i < stringy.length(); i++) {
			index = (char) (stringy.charAt(i) - 10);
			coded += index;
		}
		
		return coded;
	}
}
