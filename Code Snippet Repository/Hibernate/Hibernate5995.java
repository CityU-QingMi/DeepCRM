	@Test
	public void testOverriddenPar() throws Exception {
		File testPackage = buildOverridenPar();
		addPackageToClasspath( testPackage );

		HashMap properties = new HashMap();
		properties.put( AvailableSettings.JTA_DATASOURCE, null );
		Properties p = new Properties();
		p.load( ConfigHelper.getResourceAsStream( "/overridenpar.properties" ) );
		properties.putAll( p );
		emf = Persistence.createEntityManagerFactory( "overridenpar", properties );
		EntityManager em = emf.createEntityManager();
		org.hibernate.jpa.test.pack.overridenpar.Bug bug = new org.hibernate.jpa.test.pack.overridenpar.Bug();
		bug.setSubject( "Allow DS overriding" );
		em.getTransaction().begin();
		em.persist( bug );
		em.flush();
		em.remove( bug );
		assertNotNull( bug.getId() );
		em.getTransaction().rollback();
		em.close();
		emf.close();
	}
