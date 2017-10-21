	@Override
	public String toString() {
		final StringBuilder buf = new StringBuilder();
		buf.append( "JoinSequence{" );
		if ( rootJoinable != null ) {
			buf.append( rootJoinable )
					.append( '[' )
					.append( rootAlias )
					.append( ']' );
		}
		for ( Join join : joins ) {
			buf.append( "->" ).append( join );
		}
		return buf.append( '}' ).toString();
	}
