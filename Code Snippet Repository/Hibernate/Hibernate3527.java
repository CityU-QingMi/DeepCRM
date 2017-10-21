	protected String processDistinctKeyword(
			String sql,
			QueryParameters parameters) {
		if ( !parameters.isPassDistinctThrough() ) {
			if ( sql.startsWith( SELECT_DISTINCT ) ) {
				return SELECT + sql.substring( SELECT_DISTINCT.length() );
			}
		}
		return sql;
	}
