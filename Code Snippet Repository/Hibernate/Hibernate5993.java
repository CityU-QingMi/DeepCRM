	@Test
	public void testCfgXmlPar() throws Exception {
		File testPackage = buildCfgXmlPar();
		addPackageToClasspath( testPackage );

		emf = Persistence.createEntityManagerFactory( "cfgxmlpar", new HashMap() );

		assertTrue( emf.getProperties().containsKey( "hibernate.test-assertable-setting" ) );

		EntityManager em = emf.createEntityManager();
		Item i = new Item();
		i.setDescr( "Blah" );
		i.setName( "factory" );
		Morito m = new Morito();
		m.setPower( "SuperStrong" );
		em.getTransaction().begin();
		em.persist( i );
		em.persist( m );
		em.getTransaction().commit();

		em.getTransaction().begin();
		i = em.find( Item.class, i.getName() );
		em.remove( i );
		em.remove( em.find( Morito.class, m.getId() ) );
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
