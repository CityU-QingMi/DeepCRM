	public String addSqlHintOrComment(
			String sql,
			QueryParameters parameters,
			boolean commentsEnabled) {

		// Keep this here, rather than moving to Select.  Some Dialects may need the hint to be appended to the very
		// end or beginning of the finalized SQL statement, so wait until everything is processed.
		if ( parameters.getQueryHints() != null && parameters.getQueryHints().size() > 0 ) {
			sql = getQueryHintString( sql, parameters.getQueryHints() );
		}
		else if ( commentsEnabled && parameters.getComment() != null ){
			sql = prependComment( sql, parameters.getComment() );
		}

		return sql;
	}
