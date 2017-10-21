	@Test
	public void testDefaultParForPersistence_1_0() throws Exception {
		File testPackage = buildDefaultPar_1_0();
		addPackageToClasspath( testPackage );

		emf = Persistence.createEntityManagerFactory( "defaultpar_1_0", new HashMap() );
		EntityManager em = emf.createEntityManager();
		ApplicationServer1 as = new ApplicationServer1();
		as.setName( "JBoss AS" );
		Version1 v = new Version1();
		v.setMajor( 4 );
		v.setMinor( 0 );
		v.setMicro( 3 );
		as.setVersion( v );
		Mouse1 mouse = new Mouse1();
		mouse.setName( "mickey" );
		em.getTransaction().begin();
		em.persist( as );
		em.persist( mouse );
		assertEquals( 1, em.createNamedQuery( "allMouse_1_0" ).getResultList().size() );
		Lighter1 lighter = new Lighter1();
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
