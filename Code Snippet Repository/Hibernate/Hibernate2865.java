	@Override
	public String toStatement() {
		StringBuilder buffer = new StringBuilder();

		for ( int i = 0; i < getIds().size(); i++ ) {
			Object[] idTokens = getIds().get( i );

			if ( i > 0 ) {
				buffer.append( " or " );
			}

			buffer.append( "(" );

			for ( int j = 0; j < getColumns().length; j++ ) {
				if ( j > 0 ) {
					buffer.append( " and " );
				}
				buffer.append( getColumns()[j] );
				buffer.append( " = " );
				buffer.append( quoteIdentifier( idTokens[j] ) );

			}
			buffer.append( ")" );
		}

		return buffer.toString();
	}
