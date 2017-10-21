	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
		final Integer year = rs.getObject( names[0], Integer.class );
		final Integer month = rs.getObject( names[1], Integer.class );
		final Integer day = rs.getObject( names[2], Integer.class );
		final Integer hour = rs.getObject( names[3], Integer.class );
		final Integer minute = rs.getObject( names[4], Integer.class );
		final Integer second = rs.getObject( names[5], Integer.class );
		if ( year == null && month == null && day == null && hour == null && minute == null && second == null ) {
			return null;
		} else {
			return new CompositeDateTime( year, month, day, hour, minute, second );
		}
	}
