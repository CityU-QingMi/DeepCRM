	private boolean hasOrderBy(String sql) {
		int depth = 0;

		String lowerCaseSQL = sql.toLowerCase();

		for ( int i = lowerCaseSQL.length() - 1; i >= 0; --i ) {
			char ch = lowerCaseSQL.charAt( i );
			if ( ch == '(' ) {
				depth++;
			}
			else if ( ch == ')' ) {
				depth--;
			}
			if ( depth == 0 ) {
				if ( lowerCaseSQL.startsWith( "order by ", i ) ) {
					return true;
				}
			}
		}
		return false;
	}
