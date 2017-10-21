	@Test
	public void testExternalJar() throws Exception {
		File externalJar = buildExternalJar();
		File testPackage = buildExplicitPar();
		addPackageToClasspath( testPackage, externalJar );

		emf = Persistence.createEntityManagerFactory( "manager1", new HashMap() );
		EntityManager em = emf.createEntityManager();
		Scooter s = new Scooter();
		s.setModel( "Abadah" );
		s.setSpeed( 85l );
		em.getTransaction().begin();
		em.persist( s );
		em.getTransaction().commit();
		em.close();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		s = em.find( Scooter.class, s.getModel() );
		assertEquals( Long.valueOf( 85 ), s.getSpeed() );
		em.remove( s );
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
