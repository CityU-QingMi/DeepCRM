	@Test
	@TestForIssue( jiraKey = "")
	public void testReplacedWhileManaged() {
		createData();

		Session session = openSession();
		session.beginTransaction();
		List results = session.createQuery( "from Tranchenmodell" ).list();
		assertEquals( 1, results.size() );
		results = session.createQuery( "from Preisregelung" ).list();
		assertEquals( 1, results.size() );
		Preisregelung preisregelung = (Preisregelung) results.get( 0 );
		assertNotNull( preisregelung.getTranchenmodell() );

		// Replace with a new Tranchenmodell instance
		Tranchenmodell tranchenmodellNew = new Tranchenmodell();
		tranchenmodellNew.setId( 1952L );
		preisregelung.setTranchenmodell( tranchenmodellNew );
		tranchenmodellNew.setPreisregelung( preisregelung );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		results = session.createQuery( "from Tranchenmodell" ).list();
		assertEquals( 1, results.size() );
		Tranchenmodell tranchenmodellQueried = (Tranchenmodell) results.get( 0 );
		assertEquals( tranchenmodellNew.getId(), tranchenmodellQueried.getId() );
		results = session.createQuery( "from Preisregelung" ).list();
		assertEquals( 1, results.size() );
		Preisregelung preisregelung1Queried =  (Preisregelung) results.get( 0 );
		assertEquals( tranchenmodellQueried, preisregelung1Queried.getTranchenmodell() );
		results = session.createQuery( "from Tranche" ).list();
		assertEquals( 0, results.size() );

		session.getTransaction().commit();
		session.close();

		cleanupData();
	}
