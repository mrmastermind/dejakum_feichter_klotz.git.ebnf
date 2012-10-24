import java.io.FileNotFoundException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser pa = new Parser();
		pa.setFile("C:/doc.txt");
		pa.analyseFile();
	}
}