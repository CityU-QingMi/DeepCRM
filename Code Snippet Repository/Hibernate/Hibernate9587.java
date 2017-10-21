	@Test
	public void testZoneDateTimeWithUTCZoneOffset() {
		final ZonedDateTime expectedStartDate = ZonedDateTime.of(
				1,
				1,
				1,
				0,
				0,
				0,
				0,
				ZoneOffset.UTC
		);

		saveZoneDateTimeEventWithStartDate( expectedStartDate );

		checkSavedZonedDateTimeIsEqual( expectedStartDate );
		compareSavedZonedDateTimeWith( expectedStartDate );
	}
