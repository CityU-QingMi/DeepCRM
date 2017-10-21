	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if ( value != null ) {
			String v = (String) value;
			if ( !v.startsWith( param1 ) ) {
				v = param1 + v;
			}
			if ( !v.endsWith( param2 ) ) {
				v = v + param2;
			}
			StringType.INSTANCE.nullSafeSet( st, v, index, session );
		}
		else {
			StringType.INSTANCE.nullSafeSet( st, null, index, session );
		}
	}
