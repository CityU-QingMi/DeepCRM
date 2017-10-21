	@Override
	public String toStatement() {
		StringBuilder buffer = new StringBuilder();

		String columnNames = String.join( ",", (CharSequence[]) getColumns() );

		for ( int i = 0; i < getIds().size(); i++ ) {
			Object[] idTokens = getIds().get( i );
			if ( i > 0 ) {
				if( chunkLimit > 0 && i % chunkLimit == 0 ) {
					buffer.append( " ) or ( " );
					buffer.append( columnNames );
					buffer.append( " ) in (" );
				}
				else {
					buffer.append( "," );
				}
			}
			buffer.append( "(" );
			buffer.append( quoteIdentifier( idTokens ) );
			buffer.append( ")" );
		}

		return buffer.toString();
	}
