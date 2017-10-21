	public void logErrors() {
		if ( errors != null ) {
			for ( SAXParseException e : errors ) {
				if ( file == null ) {
					LOG.parsingXmlError( e.getLineNumber(), e.getMessage() );
				}
				else {
					LOG.parsingXmlErrorForFile( file, e.getLineNumber(), e.getMessage() );
				}
			}
		}
	}
