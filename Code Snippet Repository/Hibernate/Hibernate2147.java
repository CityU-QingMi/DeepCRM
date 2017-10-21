	private String toMetaDataText(Identifier identifier) {
		if ( identifier == null ) {
			throw new IllegalArgumentException( "Identifier cannot be null; bad usage" );
		}

		if ( identifier instanceof DatabaseIdentifier ) {
			return identifier.getText();
		}

		if ( identifier.isQuoted() ) {
			switch ( quotedCaseStrategy ) {
				case UPPER: {
					log.tracef( "Rendering quoted identifier [%s] in upper case for use in DatabaseMetaData", identifier );
					return identifier.getText().toUpperCase( Locale.ROOT );
				}
				case LOWER: {
					log.tracef( "Rendering quoted identifier [%s] in lower case for use in DatabaseMetaData", identifier );
					return identifier.getText().toLowerCase( Locale.ROOT );
				}
				default: {
					// default is mixed case
					log.tracef( "Rendering quoted identifier [%s] in mixed case for use in DatabaseMetaData", identifier );
					return identifier.getText();
				}
			}
		}
		else {
			switch ( unquotedCaseStrategy ) {
				case MIXED: {
					log.tracef( "Rendering unquoted identifier [%s] in mixed case for use in DatabaseMetaData", identifier );
					return identifier.getText();
				}
				case LOWER: {
					log.tracef( "Rendering unquoted identifier [%s] in lower case for use in DatabaseMetaData", identifier );
					return identifier.getText().toLowerCase( Locale.ROOT );
				}
				default: {
					// default is upper case
					log.tracef( "Rendering unquoted identifier [%s] in upper case for use in DatabaseMetaData", identifier );
					return identifier.getText().toUpperCase( Locale.ROOT );
				}
			}
		}
	}
