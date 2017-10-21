	@Test
	public void encodingAndBoundVariablesCapturePathElement() {
		checkCapture("{var}","f%20o","var","f o");
		checkCapture("{var1}/{var2}","f%20o/f%7Co","var1","f o","var2","f|o");
		checkCapture("{var1}/{var2}","f%20o/f%7co","var1","f o","var2","f|o"); // lower case encoding
		checkCapture("{var:foo}","foo","var","foo");
		checkCapture("{var:f o}","f%20o","var","f o"); // constraint is expressed in non encoded form
		checkCapture("{var:f.o}","f%20o","var","f o");
		checkCapture("{var:f\\|o}","f%7co","var","f|o");	
	}
