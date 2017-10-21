	@Test
	@TestForIssue( jiraKey = "" )
	public void testCurrentDate() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			Date date = (Date) session.createQuery(
				"select current_date() " +
				"from Event e " +
				"where e.id = :id")
			.setParameter( "id", event.id )
			.getSingleResult();

			assertNotNull( date );
			assertTrue( date.getTime() > 0 );

			Calendar resultCalendar = Calendar.getInstance();
			resultCalendar.setTime(date);

			assertEquals( 0, todayCalendar().compareTo(resultCalendar) );
		} );
	}
