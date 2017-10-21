	@Override
	public Object getPropertyValue(Object component, int property) throws HibernateException {
		final CompositeDateTime dateTime = (CompositeDateTime) component;
		switch ( property ) {
			case 0:
				return dateTime.getYear();

			case 1:
				return dateTime.getMonth();

			case 2:
				return dateTime.getDay();

			case 3:
				return dateTime.getHour();

			case 4:
				return dateTime.getMinute();

			case 5:
				return dateTime.getSecond();

			default:
				throw new HibernateException( "This type has only 6 fields." );
		}
	}
