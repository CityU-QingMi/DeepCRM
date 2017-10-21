	@Override
	public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
		Date date = (Date) component;
		Calendar c = GregorianCalendar.getInstance();
		c.setTime( date );

		switch ( property ) {
			case 0:
				c.set( Calendar.YEAR, (Integer) value );
			case 1:
				c.set( Calendar.MONTH, (Integer) value );
			case 2:
				c.set( Calendar.DAY_OF_MONTH, (Integer) value );
			default:
				throw new HibernateException( "Invalid property provided" );
		}
	}
