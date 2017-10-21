	private static int bindPositionalParameters(
			final PreparedStatement st,
			final QueryParameters queryParameters,
			final int start,
			final SessionImplementor session) throws SQLException, HibernateException {
		return bindPositionalParameters(
				st,
				queryParameters.getPositionalParameterValues(),
				queryParameters.getPositionalParameterTypes(),
				start,
				session
		);
	}
