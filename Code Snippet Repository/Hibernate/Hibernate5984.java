	@Test
	public void testDefaultPar() throws Exception {
		File testPackage = buildDefaultPar();
		addPackageToClasspath( testPackage );

		// run the test
		emf = Persistence.createEntityManagerFactory( "defaultpar", new HashMap() );
		EntityManager em = emf.createEntityManager();
		ApplicationServer as = new ApplicationServer();
		as.setName( "JBoss AS" );
		Version v = new Version();
		v.setMajor( 4 );
		v.setMinor( 0 );
		v.setMicro( 3 );
		as.setVersion( v );
		Mouse mouse = new Mouse();
		mouse.setName( "mickey" );
		em.getTransaction().begin();
		em.persist( as );
		em.persist( mouse );
		assertEquals( 1, em.createNamedQuery( "allMouse" ).getResultList().size() );
		Lighter lighter = new Lighter();
		lighter.name = "main";
		lighter.power = " 250 W";
		em.persist( lighter );
		em.flush();
		em.remove( lighter );
		em.remove( mouse );
		assertNotNull( as.getId() );
		em.remove( as );
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
