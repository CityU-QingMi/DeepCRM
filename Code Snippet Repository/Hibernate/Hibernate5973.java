	@Test
	@TestForIssue( jiraKey = "")
	public void testDirectAndNestedAssociationsOrphanedWhileManaged() {
		createData();

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		List results = em.createQuery( "from Tranchenmodell" ).getResultList();
		assertEquals( 1, results.size() );
		results = em.createQuery( "from Preisregelung" ).getResultList();
		assertEquals( 1, results.size() );
		Preisregelung preisregelung = (Preisregelung) results.get( 0 );
		Tranchenmodell tranchenmodell = preisregelung.getTranchenmodell();
		assertNotNull( tranchenmodell );
		assertNotNull( tranchenmodell.getX() );
		assertEquals( 2, tranchenmodell.getTranchen().size() );
		assertNotNull( tranchenmodell.getTranchen().get( 0 ).getY() );
		preisregelung.setTranchenmodell( null );
		tranchenmodell.setX( null );
		tranchenmodell.getTranchen().get( 0 ).setY( null );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();

		preisregelung = (Preisregelung) em.find( Preisregelung.class, preisregelung.getId() );
		assertNull( preisregelung.getTranchenmodell() );
		results = em.createQuery( "from Tranchenmodell" ).getResultList();
		assertEquals( 0, results.size() );
		results = em.createQuery( "from Tranche" ).getResultList();
		assertEquals( 0, results.size() );
		results = em.createQuery( "from X" ).getResultList();
		assertEquals( 0, results.size() );
		results = em.createQuery( "from Y" ).getResultList();
		assertEquals( 0, results.size() );

		results = em.createQuery( "from Preisregelung" ).getResultList();
		assertEquals( 1, results.size() );

		em.getTransaction().commit();
		em.close();

		cleanupData();
	}
