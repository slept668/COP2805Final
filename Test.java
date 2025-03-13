package cop2805;

public class Test {

	public static void main(String[] args) {
		String tester = "j[ij'()*";
		Encrypt decoderRing = new Encrypt();
		
		System.out.println(decoderRing.Encrypter(tester));

	}

}
