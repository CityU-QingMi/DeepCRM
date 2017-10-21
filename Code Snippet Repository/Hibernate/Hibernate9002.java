	@Test
	@TestForIssue( jiraKey = "")
	public void testDirectAndNestedAssociationsOrphanedWhileManaged() {
		createData();

		Session session = openSession();
		session.beginTransaction();
		List results = session.createQuery( "from Tranchenmodell" ).list();
		assertEquals( 1, results.size() );
		results = session.createQuery( "from Preisregelung" ).list();
		assertEquals( 1, results.size() );
		Preisregelung preisregelung = ( Preisregelung ) results.get( 0 );
		Tranchenmodell tranchenmodell = preisregelung.getTranchenmodell();
		assertNotNull( tranchenmodell );
		assertNotNull( tranchenmodell.getX() );
		assertEquals( 2, tranchenmodell.getTranchen().size() );
		assertNotNull( tranchenmodell.getTranchen().get( 0 ).getY() );
		preisregelung.setTranchenmodell( null );
		tranchenmodell.setX( null );
		tranchenmodell.getTranchen().get( 0 ).setY( null );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();

		preisregelung = ( Preisregelung ) session.get( Preisregelung.class, preisregelung.getId() );
		assertNull( preisregelung.getTranchenmodell() );
		results = session.createQuery( "from Tranchenmodell" ).list();
		assertEquals( 0, results.size() );
		results = session.createQuery( "from Tranche" ).list();
		assertEquals( 0, results.size() );
		results = session.createQuery( "from X" ).list();
		assertEquals( 0, results.size() );
		results = session.createQuery( "from Y" ).list();
		assertEquals( 0, results.size() );

		results = session.createQuery( "from Preisregelung" ).list();
		assertEquals( 1, results.size() );

		session.getTransaction().commit();
		session.close();

		cleanupData();
	}
