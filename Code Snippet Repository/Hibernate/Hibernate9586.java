	@Test
	public void testZoneDateTimeWithHoursZoneOffset() {
		final ZonedDateTime expectedStartDate = ZonedDateTime.of(
				2015,
				1,
				1,
				0,
				0,
				0,
				0,
				ZoneOffset.ofHours( 5 )
		);

		saveZoneDateTimeEventWithStartDate( expectedStartDate );

		checkSavedZonedDateTimeIsEqual( expectedStartDate );
		compareSavedZonedDateTimeWith( expectedStartDate );
	}
