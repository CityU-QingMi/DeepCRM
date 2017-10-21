	String renderScalarIdentifierSelect(int i) {
		checkInitialized();

		final String idPropertyName = getIdentifierPropertyName();
		String[] cols = getPropertyMapping( idPropertyName ).toColumns( getTableAlias(), idPropertyName );

		StringBuilder buf = new StringBuilder();
		// For property references generate <tablealias>.<columnname> as <projectionalias>
		for ( int j = 0; j < cols.length; j++ ) {
			String column = cols[j];
			if ( j > 0 ) {
				buf.append( ", " );
			}
			buf.append( column ).append( " as " ).append( NameGenerator.scalarName( i, j ) );
		}
		return buf.toString();
	}
