	private void checkSavedZonedDateTimeIsEqual(ZonedDateTime startdate) {
		final Session s = openSession();
		try {
			final ZonedDateTimeEvent zonedDateTimeEvent = s.get( ZonedDateTimeEvent.class, 1L );
			assertThat( zonedDateTimeEvent.startDate.isEqual( startdate ), is( true ) );
		}
		finally {
			s.close();
		}
	}
