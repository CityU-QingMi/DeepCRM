	@Test
	public void testOffsetDateTimeWithUTCZoneOffset() {
		final OffsetDateTime expectedStartDate = OffsetDateTime.of(
				1,
				1,
				1,
				0,
				0,
				0,
				0,
				ZoneOffset.UTC
		);

		saveOffsetDateTimeEventWithStartDate( expectedStartDate );

		checkSavedOffsetDateTimeIsEqual( expectedStartDate );
		compareSavedOffsetDateTimeWith( expectedStartDate );
	}
