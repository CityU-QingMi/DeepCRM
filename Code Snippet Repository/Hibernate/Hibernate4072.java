	@Override
	public Object readIndex(ResultSet rs, String[] aliases, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		Object index = getIndexType().nullSafeGet( rs, aliases, session, null );
		if ( index == null ) {
			throw new HibernateException( "null index column for collection: " + role );
		}
		index = decrementIndexByBase( index );
		return index;
	}
