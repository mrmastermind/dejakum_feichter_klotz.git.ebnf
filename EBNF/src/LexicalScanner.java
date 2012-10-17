import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class LexicalScanner {

	private ArrayList<Token> tokenList = new ArrayList<Token>();
	private int column, line, pos;
	private String text;
	private boolean eof;
	
	private void print(){
		for(Token t:tokenList){
			System.out.println(t.toString());
		}
	}
	
	private char getChar(){
		if (pos >= text.length()){
			eof = true;
			return '\0';
		}
		//After a semicolon the compiler interprets a new line
		if(this.text.charAt(pos)==';'){		
			line++;
			column=0;
		}
		return text.charAt(pos);
	}
	private void nextChar(){
		pos++;
	}
	private void skipWhiteSpace(){
		if(Character.isWhitespace(getChar())){
			this.nextChar();
		}
	}
	private void readNumber(Token t){
		char c = getChar();
		int holdpos = pos;
		while((!eof) && (Character.isDigit(c))){
			this.nextChar();
			c=this.getChar();
		}
		
		t.setText(text.substring(holdpos, pos));
		t.setCode(TokenCode.eNumber);
	}
	private void readName(Token t){
		char c = getChar();
		int holdpos = pos;
		//there has to be a letter on the first position --> semantic rules
		//this first letter is defined in the class getNext()
		while((!eof) && ((Character.isLetterOrDigit(c)))){
			this.nextChar();
			c=this.getChar();
		}		
		
		t.setText(text.substring(holdpos, pos));
		t.setCode(TokenCode.eName);
	}
	private void readOperatorDelimiter(Token t){
		char c = getChar();
		int holdpos = pos;
		//cast the caracter to ascii for special operators 
		int myAscii = (int) c;
		
		if(c=='+'){
			t.setCode(TokenCode.ePlus);
		}else if(c=='-'){
			t.setCode(TokenCode.eMinus);
		}else if(c=='*'){
			t.setCode(TokenCode.eMultiplication);
		}else if(c=='/'){
			t.setCode(TokenCode.eDivide);
		}else if(c=='='){
			t.setCode(TokenCode.eEquals);
		}else if(c==';'){
			t.setCode(TokenCode.eSemicolon);
		}else if(myAscii == 0x22){
			t.setCode(TokenCode.eApostrophe);
		}else if((myAscii == 0x28) || (myAscii == 0x29) ){
			t.setCode(TokenCode.eRoundBracket);
		}else if((myAscii == 0x7B) || (myAscii == 0x7D) ){
			t.setCode(TokenCode.eCurlyBracket);
		}else{
			//there is only mod left
			t.setCode(TokenCode.eMod);
			pos = pos+3;
		}
		//does it really work(with the arguments "pos,pos")?!
		t.setText(text.substring(holdpos, pos));
	}
	private void handleKeyword(Token t, int index){
		char c = getChar();
		int holdpos = pos;
		
		while((!eof) && (Character.isLetter(c))){
			this.nextChar();
			c=this.getChar();
		}
		
		t.setText(text.substring(holdpos, pos));
		if(index==0){
			t.setCode(TokenCode.eProgstart);
		}
		if(index==1){
			t.setCode(TokenCode.eProgend);
		}
		if(index==2){
			t.setCode(TokenCode.eConst);
		}
		if(index==3){
			t.setCode(TokenCode.eVar);
		}
		if(index==4){
			t.setCode(TokenCode.ePrint);
		}
	}
	
	public void readFile(String path)throws FileNotFoundException {
		java.util.Scanner sc = new java.util.Scanner(new File(path));
		sc.useDelimiter("//z");
		text = sc.next();
		pos = 0;
		line = 1;
		column = 0;
	}
	
	public boolean readEof(){
		return eof;
	}
	
	public Token getNext(){
		skipWhiteSpace();
		column++;
		char c = this.getChar();
		Token t = new Token(line, column);
		if(isOperatorDelimiter()){
			this.readOperatorDelimiter(t);
		}
		else if(Character.isLetter(c)){
			if(c=='p'){
				if(isProgstart()){
					this.handleKeyword(t, 0);
				}
				else if(isProgend()){
					this.handleKeyword(t, 1);
				}
				else if(isPrint()){
					this.handleKeyword(t, 4);
				}
			}
			else if(c=='c'){
				if(isConst()){
					this.handleKeyword(t, 2);
				}
			}
			else if(c=='v'){
				if(isVar()){
					this.handleKeyword(t, 3);
				}
			}
			this.readName(t);
		}
		else if(Character.isDigit(c)){
			this.readNumber(t);
		}else{
			t.setCode(TokenCode.eError);
			t.setText(text.substring(pos, pos));
		}
		return t;
	}
	
	public boolean isOperatorDelimiter(){
		char c=getChar();
		if((c=='+')||(c=='-')||(c=='*')||(c=='/')||(c=='=')
				||(c==';')||(c=='{')||(c=='}')||(c=='(')||(c==')')
				||(c=='"')){
		return true;
		}
		//now comes 'mod'(modulo)
		else if(c=='m'){
			nextChar();
			c=getChar();
			if(c=='o'){
				nextChar();
				c=getChar();
				 if(c=='d'){
					return true;
				}
				 else{
					 //if it's not modulo, then set back the pos again
					 pos=pos-2;
					 return false;
				 }
			}
			else{
				pos--;
				return false;
			}
		}
		else{
			return false;
		}
	}
	/*public boolean isKeyword(){
		 if(c=='m'){
			nextChar();
			c=getChar();
			if(c=='o'){
				nextChar();
				c=getChar();
				 if(c=='d'){
					return true;
				}
				 else{
					 return false;
				 }
			}
			else{
				return false;
			}
		}
		else{
			return false;
	}*/
		 
	public boolean isProgstart(){
		int length=9;
		char[] keyword = new char[length];
		for(int i =0;i<length;i++){
			keyword[i]=getChar();
			nextChar();
		}
		return String.valueOf(keyword).equals("progstart");
	}
	
	public boolean isProgend(){
		int length=7;
		char[] keyword = new char[length];
		for(int i =0;i<length;i++){
			keyword[i]=getChar();
			nextChar();
		}
		return String.valueOf(keyword).equals("progend");
	}
	
	public boolean isConst(){
		int length=5;
		char[] keyword = new char[length];
		for(int i =0;i<length;i++){
			keyword[i]=getChar();
			nextChar();
		}
		return String.valueOf(keyword).equals("const");
	}

	public boolean isVar(){
		int length=3;
		char[] keyword = new char[length];
		for(int i =0;i<length;i++){
			keyword[i]=getChar();
			nextChar();
		}
		return String.valueOf(keyword).equals("var");
	}

	public boolean isPrint(){
		int length=5;
		char[] keyword = new char[length];
		for(int i =0;i<length;i++){
			keyword[i]=getChar();
			nextChar();
		}
		return String.valueOf(keyword).equals("print");
	}

	public void addToken(){
		this.getTokenList().add(getNext());
	}
	
	public ArrayList<Token> getTokenList() {
		return tokenList;
	}
	
	public String getTokenInformation(Token t){
		return t.toString();
	}
	
}
