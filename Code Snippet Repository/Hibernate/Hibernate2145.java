	@Override
	public Identifier normalizeQuoting(Identifier identifier) {
		log.tracef( "Normalizing identifier quoting [%s]", identifier );

		if ( identifier == null ) {
			return null;
		}

		if ( identifier.isQuoted() ) {
			return identifier;
		}

		if ( globallyQuoteIdentifiers ) {
			log.tracef( "Forcing identifier [%s] to quoted for global quoting", identifier );
			return Identifier.toIdentifier( identifier.getText(), true );
		}

		if ( autoQuoteKeywords && isReservedWord( identifier.getText() ) ) {
			log.tracef( "Forcing identifier [%s] to quoted as recognized reserved word", identifier );
			return Identifier.toIdentifier( identifier.getText(), true );
		}

		return identifier;
	}
