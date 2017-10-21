	@Test
	public void testRetrievingEntityByZoneDateTime() {

		final ZonedDateTime startDate = ZonedDateTime.of(
				1,
				1,
				1,
				0,
				0,
				0,
				0,
				ZoneOffset.ofHours( 3 )
		);

		saveZoneDateTimeEventWithStartDate( startDate );

		final Session s = openSession();
		try {
			Query query = s.createQuery( "from ZonedDateTimeEvent o where o.startDate = :date" );
			query.setParameter( "date", startDate, ZonedDateTimeType.INSTANCE );
			List<ZonedDateTimeEvent> list = query.list();
			assertThat( list.size(), is( 1 ) );
		}
		finally {
			s.close();
		}
	}
