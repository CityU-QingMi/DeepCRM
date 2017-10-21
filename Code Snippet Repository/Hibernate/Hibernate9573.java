	private void compareSavedOffsetDateTimeWith(OffsetDateTime startdate) {
		final Session s = openSession();
		try {
			final OffsetDateTimeEvent offsetDateEvent = s.get( OffsetDateTimeEvent.class, 1L );
			assertThat(
					OffsetDateTimeType.INSTANCE.getComparator().compare( offsetDateEvent.startDate, startdate ),
					is( 0 )
			);
		}
		finally {
			s.close();
		}
	}
