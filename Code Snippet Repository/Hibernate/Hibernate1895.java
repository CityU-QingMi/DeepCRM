	protected void addTopExpression(StringBuilder sql) {
		// We should use either of these which come first (SELECT or SELECT DISTINCT).
		final int selectPos = shallowIndexOfPattern( sql, SELECT_PATTERN, 0 );
		final int selectDistinctPos = shallowIndexOfPattern( sql, SELECT_DISTINCT_PATTERN, 0 );
		if ( selectPos == selectDistinctPos ) {
			// Place TOP after SELECT DISTINCT
			sql.insert( selectDistinctPos + SELECT_DISTINCT.length(), " TOP(?)" );
		}
		else {
			// Place TOP after SELECT
			sql.insert( selectPos + SELECT.length(), " TOP(?)" );
		}
		topAdded = true;
	}
