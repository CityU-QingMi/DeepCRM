	private static int bindPositionalParameters(
			final PreparedStatement st,
			final Object[] values,
			final Type[] types,
			final int start,
			final SessionImplementor session) throws SQLException, HibernateException {
		int span = 0;
		for ( int i = 0; i < values.length; i++ ) {
			types[i].nullSafeSet( st, values[i], start + span, session );
			span += types[i].getColumnSpan( session.getFactory() );
		}
		return span;
	}
