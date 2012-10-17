
public enum TokenCode {

	eNumber,
	eName,

	//Keywords
	eProgstart,
	eProgend,
	eConst,
	eVar,
	ePrint,
	
	//Operators
	ePlus,
	eMinus,
	eMultiplication,
	eDivide,
	eMod,
	eEquals,

	//Delimiters
	eSemicolon,
	eApostrophe,
	eRoundBracket,
	eCurlyBracket,

	eEOF,	//End of File
	eError	//Error
}
