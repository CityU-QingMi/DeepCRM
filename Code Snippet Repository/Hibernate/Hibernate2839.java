	protected String generateIdSubselect(Queryable persister) {
		return new StringBuilder()
				.append( "select " )
				.append( String.join(
						", ",
						(CharSequence[]) persister.getIdentifierColumnNames()
				) )
				.append( " from " )
				.append( determineIdTableName( persister ) )
				.toString();
	}
