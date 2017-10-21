	public String toFragmentString() {
		StringBuilder buf = new StringBuilder( cases.size() * 15 + 10 );
		buf.append( "case" ); 								//$NON-NLS-1
		Iterator iter = cases.entrySet().iterator();
		while ( iter.hasNext() ) {
			Map.Entry me = ( Map.Entry ) iter.next();
			buf.append( " when " )//$NON-NLS-1
					.append( me.getKey() )
					.append( " is not null then " )//$NON-NLS-1
					.append( me.getValue() );
		}
		// null is not considered the same type as Integer.
		buf.append( " else -1" );								//$NON-NLS-1
		buf.append( " end" );									//$NON-NLS-1
		if ( returnColumnName != null ) {
			buf.append( " as " )//$NON-NLS-1
					.append( returnColumnName );
		}
		return buf.toString();
	}
