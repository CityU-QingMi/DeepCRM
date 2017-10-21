	public static int bindQueryParameters(
			final PreparedStatement st,
			final QueryParameters queryParameters,
			final int start,
			final NamedParameterSource source,
			SessionImplementor session) throws SQLException, HibernateException {
		int col = start;
		col += bindPositionalParameters( st, queryParameters, col, session );
		col += bindNamedParameters( st, queryParameters, col, source, session );
		return col;
	}
