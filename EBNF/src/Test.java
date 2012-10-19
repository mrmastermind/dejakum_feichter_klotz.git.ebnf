public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "pleaseFillIn";
		LexicalScanner lx = new LexicalScanner();
		lx.readFile(path);
		lx.print();
	}
}
