	@Test
	public void testRetrievingEntityByOffsetDateTime() {

		final OffsetDateTime startDate = OffsetDateTime.of(
				1,
				1,
				1,
				0,
				0,
				0,
				0,
				ZoneOffset.ofHours( 3 )
		);

		saveOffsetDateTimeEventWithStartDate( startDate );

		final Session s = openSession();
		try {
			Query query = s.createQuery( "from OffsetDateTimeEvent o where o.startDate = :date" );
			query.setParameter( "date", startDate, OffsetDateTimeType.INSTANCE );
			List<OffsetDateTimeEvent> list = query.list();
			assertThat( list.size(), is( 1 ) );
		}
		finally {
			s.close();
		}
	}
