	private int getInsertPosition(String sql) {
		int position = sql.length() - 1;
		for ( ; position > 0; --position ) {
			char ch = sql.charAt( position );
			if ( ch != ';' && ch != ' ' && ch != '\r' && ch != '\n' ) {
				break;
			}
		}
		return position + 1;
	}
