	@Override
	public String toStatement() {
		StringBuilder buffer = new StringBuilder();

		String columnNames = String.join( ",", (CharSequence[]) getColumns() );

		buffer.append( "select " ).append( columnNames ).append(
				" from ( values " );

		for ( int i = 0; i < getIds().size(); i++ ) {
			Object[] idTokens = getIds().get( i );
			if ( i > 0 ) {
				buffer.append( "," );
			}
			buffer.append( "(" );
			buffer.append( quoteIdentifier( idTokens ) );
			buffer.append( ")" );
		}
		buffer.append( ") as HT (" ).append( columnNames ).append( ") " );

		return buffer.toString();
	}
