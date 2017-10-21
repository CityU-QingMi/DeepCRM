	@Test
	public void testOffsetDateTimeWithHoursZoneOffset() {
		final OffsetDateTime expectedStartDate = OffsetDateTime.of(
				2015,
				1,
				1,
				0,
				0,
				0,
				0,
				ZoneOffset.ofHours( 5 )
		);

		saveOffsetDateTimeEventWithStartDate( expectedStartDate );

		checkSavedOffsetDateTimeIsEqual( expectedStartDate );
		compareSavedOffsetDateTimeWith( expectedStartDate );
	}
