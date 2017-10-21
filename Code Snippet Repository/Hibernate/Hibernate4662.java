	public String toFragmentString() {
		StringBuilder buf = new StringBuilder( lhs.length * 10 );
		for ( int i=0; i<lhs.length; i++ ) {
			buf.append(tableAlias)
				.append('.')
				.append( lhs[i] )
				.append(op)
				.append( rhs[i] );
			if (i<lhs.length-1) {
				buf.append(" and ");
			}
		}
		return buf.toString();
	}
