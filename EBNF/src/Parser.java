import java.io.FileNotFoundException;

public class Parser {
	private LexicalScanner sc;
	private Token t;
	
	//Read the File with the LexicalScanner Class
	public void setFile(String path){
		try {
			sc.readFile(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Splits the text up into tokens
	public void analyseFile(){
		sc.addToken();
		//there is a loop missing, that goes through the whole amount of tokens
	}
	
	//Shows error message
	//The TokenCode c 
	private void syntaxError(TokenCode c){
		System.err.println(sc.getTokenInformation(t));
	}
	
	//Returns true if the next Token is allowed
	private boolean acceptToken(TokenCode c){
		return t.getCode() == c;
	}
	
	//The next token has to match with the TokenCode c
	//If an error occurs, call the syntaxError() Method
	private boolean expectToken(TokenCode c){
		boolean temp = acceptToken(c);
		if(!temp){
			syntaxError(c);
		}
		t = sc.getNext();
		return temp;
	}
	
	private void NONTerminal(){
		
	}	
	
	private void start(){
		expectToken(TokenCode.eProgstart);
		expectToken(TokenCode.eName);
		expectToken(TokenCode.eSemicolon);
		while(){
			acceptToken(TokenCode.eConst);
		}
		while(){
		acceptToken(TokenCode.eVar);
		}
		expectToken(TokenCode.eCurlyBracket);
	}
}
