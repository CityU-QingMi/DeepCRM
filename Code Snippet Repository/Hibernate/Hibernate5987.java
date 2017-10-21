	@Test
	public void testRelativeJarReferences() throws Exception {
		File externalJar = buildExternalJar2();
		File testPackage = buildExplicitPar2();
		addPackageToClasspath( testPackage, externalJar );

		// if the jar cannot be resolved, this call should fail
		emf = Persistence.createEntityManagerFactory( "manager1", new HashMap() );

		// but to make sure, also verify that the entity defined in the external jar was found
		emf.getMetamodel().entity( Airplane.class );
		emf.getMetamodel().entity( Scooter.class );

		// additionally, try to use them
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
