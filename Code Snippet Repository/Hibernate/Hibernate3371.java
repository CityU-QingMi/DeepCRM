	private void validate(Document document) {
		// todo : add ability to disable validation...

		final Validator validator;
		final String version = document.getDocumentElement().getAttribute( "version" );
		if ( "2.1".equals( version ) ) {
			validator = v21Schema().newValidator();
		}
		else if ( "2.0".equals( version ) ) {
			validator = v2Schema().newValidator();
		}
		else if ( "1.0".equals(  version ) ) {
			validator = v1Schema().newValidator();
		}
		else {
			throw new PersistenceException( "Unrecognized persistence.xml version [" + version + "]" );
		}

		List<SAXException> errors = new ArrayList<SAXException>();
		validator.setErrorHandler( new ErrorHandlerImpl( errors ) );
		try {
			validator.validate( new DOMSource( document ) );
		}
		catch (SAXException e) {
			errors.add( e );
		}
		catch (IOException e) {
			throw new PersistenceException( "Unable to validate persistence.xml", e );
		}

		if ( errors.size() != 0 ) {
			//report all errors in the exception
			StringBuilder errorMessage = new StringBuilder( );
			for ( SAXException error : errors ) {
				errorMessage.append( extractInfo( error ) ).append( '\n' );
			}
			throw new PersistenceException( "Invalid persistence.xml.\n" + errorMessage.toString() );
		}
	}
