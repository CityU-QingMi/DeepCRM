	protected String quoteIdentifier(Object... value) {
		if ( value.length == 1 ) {
			return quoteIdentifier( value[0], identifierType );
		}
		else {
			if ( identifierType instanceof CompositeType ) {
				CompositeType compositeType = (CompositeType) identifierType;
				List<String> quotedIdentifiers = new ArrayList<>();

				for ( int i = 0; i < value.length; i++ ) {
					quotedIdentifiers.add(quoteIdentifier( value[i], compositeType.getSubtypes()[i] ));
				}
				return String.join( ",", quotedIdentifiers );
			}
			else {
				throw new IllegalArgumentException("Composite identifier does not implement CompositeType");
			}
		}
	}
