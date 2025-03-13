package cop2805;

public class Test {

	public static void main(String[] args) {
		String encryptedTest = "j[ij'()*";
		String decryptedTest = "test1234";
		Encrypt decoderRing = new Encrypt();
		
		System.out.println("After encryption: " + decoderRing.Encrypter(decryptedTest));
		System.out.println("Before encryption: " + decoderRing.Decrypter(encryptedTest));

	}

}
