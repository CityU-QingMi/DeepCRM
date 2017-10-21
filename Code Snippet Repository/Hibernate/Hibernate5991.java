	@Test
	public void testExplodedPar() throws Exception {
		File testPackage = buildExplodedPar();
		addPackageToClasspath( testPackage );

		emf = Persistence.createEntityManagerFactory( "explodedpar", new HashMap() );
		EntityManager em = emf.createEntityManager();
		Carpet carpet = new Carpet();
		Elephant el = new Elephant();
		el.setName( "Dumbo" );
		carpet.setCountry( "Turkey" );
		em.getTransaction().begin();
		em.persist( carpet );
		em.persist( el );
		assertEquals( 1, em.createNamedQuery( "allCarpet" ).getResultList().size() );
		assertNotNull( carpet.getId() );
		em.remove( carpet );
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
