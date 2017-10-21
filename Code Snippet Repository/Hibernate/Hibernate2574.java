	@Override
	protected boolean isNonQualifiedPropertyRef(AST ident) {
		final String identText = ident.getText();
		if ( currentFromClause.isFromElementAlias( identText ) ) {
			return false;
		}

		List fromElements = currentFromClause.getExplicitFromElements();
		if ( fromElements.size() == 1 ) {
			final FromElement fromElement = (FromElement) fromElements.get( 0 );
			try {
				LOG.tracev( "Attempting to resolve property [{0}] as a non-qualified ref", identText );
				return fromElement.getPropertyMapping( identText ).toType( identText ) != null;
			}
			catch (QueryException e) {
				// Should mean that no such property was found
			}
		}

		return false;
	}
