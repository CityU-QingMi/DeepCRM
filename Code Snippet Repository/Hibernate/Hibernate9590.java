	private void compareSavedZonedDateTimeWith(ZonedDateTime startdate) {
		final Session s = openSession();
		try {
			final ZonedDateTimeEvent zonedDateTimeEvent = s.get( ZonedDateTimeEvent.class, 1L );
			assertThat(
					ZonedDateTimeType.INSTANCE.getComparator().compare( zonedDateTimeEvent.startDate, startdate ),
					is( 0 )
			);
		}
		finally {
			s.close();
		}
	}
