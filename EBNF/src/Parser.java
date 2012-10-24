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
	//test
	private void NONTerminal(){
		
	}	
	
	private void start(){
		this.expectToken(TokenCode.eProgstart);
		this.expectToken(TokenCode.eName);
		this.expectToken(TokenCode.eSemicolon);
		while(){
			this.const();
		}
		while(){
			this.var();
		}
		this.expectToken(TokenCode.eCurlyBracket);
		this.statments();
		this.expectToken(TokenCode.eCurlyBracket);
		this.expectToken(TokenCode.eProgend);
		this.expectToken(TokenCode.eSemicolon);
	}
	//const is allready used within java
	private void const(){
		
	}
	private void var(){
		
	}
	private void statments(){
		
	}
	private void statment(){
	
	}
	private void funcCall(){
		
	}
	private void assignment(){
		
	}
	private void expression(){
		
	}
	private void faktor(){
		
	}
	private void term(){
		
	}
	private void name(){
			
	}
	private void number(){
		
	}
	private void digit(){
		
	}
	private void letter(){
		
	}
	
}
