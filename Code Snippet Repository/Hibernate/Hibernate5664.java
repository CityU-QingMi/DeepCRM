	@Override
	public Object getPropertyValue(Object component, int property) throws HibernateException {
		Date date = (Date) component;
		Calendar c = GregorianCalendar.getInstance();
		c.setTime( date );

		switch ( property ) {
			case 0:
				return c.get( Calendar.YEAR );
			case 1:
				return c.get( Calendar.MONTH );
			case 2:
				return c.get( Calendar.DAY_OF_MONTH );
		}

		throw new HibernateException( "Invalid property provided" );
	}
