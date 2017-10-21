	public String toWhereClause() {
		StringBuilder buf = new StringBuilder( whereTokens.size() * 5 );
		Iterator<String> iter = whereTokens.iterator();
		while ( iter.hasNext() ) {
			buf.append( iter.next() );
			if ( iter.hasNext() ) {
				buf.append( ' ' );
			}
		}
		return buf.toString();
	}
