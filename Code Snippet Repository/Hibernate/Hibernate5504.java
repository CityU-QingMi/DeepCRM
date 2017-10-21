	@PostLoad
	public void calculateAge() {
		Calendar birth = new GregorianCalendar();
		birth.setTime( dateOfBirth );
		Calendar now = new GregorianCalendar();
		now.setTime( new Date() );
		int adjust = 0;
		if ( now.get( Calendar.DAY_OF_YEAR ) - birth.get( Calendar.DAY_OF_YEAR ) < 0 ) {
			adjust = -1;
		}
		age = now.get( Calendar.YEAR ) - birth.get( Calendar.YEAR ) + adjust;
	}
