	@Test
	@TestForIssue( jiraKey = "" )
	public void testCurrentTimestamp() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			int tries = 2;
			while ( tries-- > 0 ) {
				Timestamp timestamp = (Timestamp) session.createQuery(
					"select current_timestamp() " +
					"from Event e " +
					"where e.id = :id" )
				.setParameter( "id", event.id )
				.getSingleResult();

				assertNotNull( timestamp );
				assertTrue( timestamp != null && timestamp.getTime() > 0 );

				Calendar resultCalendar = Calendar.getInstance();
				resultCalendar.setTime( timestamp );

				long millis = resultCalendar.getTime().getTime() - todayCalendar().getTime().getTime();

				if(millis == 0) {
					//What are the odds that ou've run this test exactly at midnight?
					try {
						Thread.sleep( 1000 );
					}
					catch ( InterruptedException ignore ) {}
					continue;
				}

				assertTrue( millis > 0 );
			}
		} );
	}
