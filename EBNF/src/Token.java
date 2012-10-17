//A Text is splitted into pieces. They are named tokens.
public class Token {
	
	private int column, line;
	private TokenCode code;
	private String text;
	
	public Token(int c, int l){
		this.setColumn(c);
		this.setLine(l);
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}
	/**
	 * @param line the line to set
	 */
	public void setLine(int line) {
		this.line = line;
	}
	/**
	 * @return the code
	 */
	public TokenCode getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(TokenCode code) {
		this.code = code;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Token [column=" + column + ", line=" + line + ", code=" + code
				+ ", text=" + text + "]";
	}
	
}
