	@Test
	@TestForIssue(jiraKey = "")
	public void testUpdateCollectionOfElements() throws Exception {
		Session s = openSession();

		s.getTransaction().begin();

		Poi poi1 = new Poi( "Poi 1" );
		Poi poi2 = new Poi( "Poi 2" );

		s.save( poi1 );
		s.save( poi2 );

		RaceExecution race = new RaceExecution();

		s.save( race );

		Date currentTime = new Date();

		race.arriveToPoi( poi1, currentTime );
		race.expectedArrive( poi2, new Date( currentTime.getTime() + 60 * 1000 ) );

		s.flush();

		assertEquals( 2, race.getPoiArrival().size() );

		StatementsCounterListener statementsCounterListener = new StatementsCounterListener();

		s.addEventListeners( statementsCounterListener );

		race.arriveToPoi( poi2, new Date( currentTime.getTime() + 2 * 60 * 1000 ) );

		s.flush();

		assertEquals( 2, race.getPoiArrival().size() );

		// There is should be one UPDATE statement. Without fix there is one DELETE and two INSERT-s.

		assertEquals( 1, statementsCounterListener.statements );

		s.getTransaction().rollback();
		s.close();
	}
