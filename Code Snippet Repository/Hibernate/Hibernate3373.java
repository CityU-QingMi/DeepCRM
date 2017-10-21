	private static String extractInfo(SAXException error) {
		if ( error instanceof SAXParseException ) {
			return "Error parsing XML [line : " + ( (SAXParseException) error ).getLineNumber()
					+ ", column : " + ( (SAXParseException) error ).getColumnNumber()
					+ "] : " + error.getMessage();
		}
		else {
			return "Error parsing XML : " + error.getMessage();
		}
	}
