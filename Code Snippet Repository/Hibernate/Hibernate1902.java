	private String applyOffsetFetch(RowSelection selection, String sql, int position) {
		usedOffsetFetch = true;

		StringBuilder sb = new StringBuilder();
		sb.append( sql.substring( 0, position ) );
		sb.append( getOffsetFetch( selection ) );
		if ( position > sql.length() ) {
			sb.append( sql.substring( position - 1 ) );
		}

		return sb.toString();
	}
