	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
		Date date = new Date();
		Calendar c = GregorianCalendar.getInstance();
		c.setTime( date );

		Integer year = StandardBasicTypes.INTEGER.nullSafeGet( rs, names[0], session );
		Integer month = StandardBasicTypes.INTEGER.nullSafeGet( rs, names[1], session );
		Integer day = StandardBasicTypes.INTEGER.nullSafeGet( rs, names[2], session );

		c.set( year, month, day );

		return date;
	}
