	public Object nullSafeGet(
			final ResultSet rs, final String[] names,
			final SharedSessionContractImplementor session,
			final Object owner) throws HibernateException, SQLException {
		final String prop1 = rs.getString( names[0] );
		if ( prop1 == null ) {
			return null;
		}
		final int prop2 = rs.getInt( names[1] );

		return new Component( prop1, prop2 );
	}
