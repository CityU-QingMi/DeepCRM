	@Test
	public void testListenersDefaultPar() throws Exception {
		File testPackage = buildDefaultPar();
		addPackageToClasspath( testPackage );

		IncrementListener.reset();
		OtherIncrementListener.reset();
		emf = Persistence.createEntityManagerFactory( "defaultpar", new HashMap() );
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ApplicationServer as = new ApplicationServer();
		as.setName( "JBoss AS" );
		Version v = new Version();
		v.setMajor( 4 );
		v.setMinor( 0 );
		v.setMicro( 3 );
		as.setVersion( v );
		em.persist( as );
		em.flush();
		assertEquals( "Failure in default listeners", 1, IncrementListener.getIncrement() );
		assertEquals( "Failure in XML overriden listeners", 1, OtherIncrementListener.getIncrement() );

		Mouse mouse = new Mouse();
		mouse.setName( "mickey" );
		em.persist( mouse );
		em.flush();
		assertEquals( "Failure in @ExcludeDefaultListeners", 1, IncrementListener.getIncrement() );
		assertEquals( 1, OtherIncrementListener.getIncrement() );

		Money money = new Money();
		em.persist( money );
		em.flush();
		assertEquals( "Failure in @ExcludeDefaultListeners", 2, IncrementListener.getIncrement() );
		assertEquals( 1, OtherIncrementListener.getIncrement() );

		em.getTransaction().rollback();
		em.close();
		emf.close();
	}
