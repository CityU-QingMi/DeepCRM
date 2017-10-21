	Type getIdentifierType(final SharedSessionContractImplementor session) {
		final Type type = associatedIdentifierType;
		if ( type == null ) {
			associatedIdentifierType = getIdentifierType( session.getFactory() );
			return associatedIdentifierType;
		}
		else {
			return type;
		}
	}
