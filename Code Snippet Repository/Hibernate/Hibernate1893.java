	private int getSelectColumnsStartPosition(StringBuilder sb) {
		final int startPos = getSelectStartPosition( sb );
		// adjustment for 'select distinct ' and 'select '.
		final String sql = sb.toString().substring( startPos ).toLowerCase();
		if ( sql.startsWith( SELECT_DISTINCT_SPACE ) ) {
			return ( startPos + SELECT_DISTINCT_SPACE.length() );
		}
		else if ( sql.startsWith( SELECT_SPACE ) ) {
			return ( startPos + SELECT_SPACE.length() );
		}
		return startPos;
	}
