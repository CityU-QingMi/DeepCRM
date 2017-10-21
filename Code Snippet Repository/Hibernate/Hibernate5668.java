	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
		Date date = new Date();
		Calendar c = GregorianCalendar.getInstance();
		c.setTime( date );

		StandardBasicTypes.INTEGER.nullSafeSet( st, c.get( Calendar.YEAR ), index, session );
		StandardBasicTypes.INTEGER.nullSafeSet( st, c.get( Calendar.MONTH ), index + 1, session );
		StandardBasicTypes.INTEGER.nullSafeSet( st, c.get( Calendar.DAY_OF_MONTH ), index + 2, session );
	}
