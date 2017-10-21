	private String getColumnPositionsString(int scalarColumnIndex ) {
		int startPosition = getWalker().getSelectClause().getColumnNamesStartPosition( scalarColumnIndex );
		StringBuilder buf = new StringBuilder();
		int nColumns = getWalker().getSelectClause().getColumnNames()[ scalarColumnIndex ].length;
		for ( int i = startPosition; i < startPosition + nColumns; i++ ) {
			if ( i > startPosition ) {
				buf.append( ", " );
			}
			buf.append( i );
		}
		return buf.toString();
	}
