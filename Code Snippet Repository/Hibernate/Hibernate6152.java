	@SuppressWarnings("")
	private void doTest(Map settings) {
		// We want a fresh db after emf close
		// Unfortunately we have to use this dirty hack because the db seems not to be closed otherwise
		settings.put( "hibernate.connection.url", "jdbc:h2:mem:db-schemagen" + schemagenNumber++
				+ ";MVCC=TRUE;LOCK_TIMEOUT=10000" );
		EntityManagerFactoryBuilder emfb = Bootstrap.getEntityManagerFactoryBuilder( buildPersistenceUnitDescriptor(),
																					 settings );
		EntityManagerFactory emf = emfb.build();
		try {
			EntityManager em = emf.createEntityManager();
			try {
				Assert.assertNotNull( em.find( Item.class, encodedName() ) );
			}
			finally {
				em.close();
			}
		}
		finally {
			emf.close();
			emfb.cancel();
		}
	}
