	@Test
	public void testExcludeHbmPar() throws Exception {
		File testPackage = buildExcludeHbmPar();
		addPackageToClasspath( testPackage );

		try {
			emf = Persistence.createEntityManagerFactory( "excludehbmpar", new HashMap() );
		}
		catch ( PersistenceException e ) {
			emf.close();
			Throwable nested = e.getCause();
			if ( nested == null ) {
				throw e;
			}
			nested = nested.getCause();
			if ( nested == null ) {
				throw e;
			}
			if ( !( nested instanceof ClassNotFoundException ) ) {
				throw e;
			}
			fail( "Try to process hbm file: " + e.getMessage() );

		}
		EntityManager em = emf.createEntityManager();
		Caipirinha s = new Caipirinha( "Strong" );
		em.getTransaction().begin();
		em.persist( s );
		em.getTransaction().commit();

		em.getTransaction().begin();
		s = em.find( Caipirinha.class, s.getId() );
		em.remove( s );
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
