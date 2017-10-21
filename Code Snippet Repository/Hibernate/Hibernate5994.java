	@Test
	public void testSpacePar() throws Exception {
		File testPackage = buildSpacePar();
		addPackageToClasspath( testPackage );

		emf = Persistence.createEntityManagerFactory( "space par", new HashMap() );
		EntityManager em = emf.createEntityManager();
		org.hibernate.jpa.test.pack.spacepar.Bug bug = new org.hibernate.jpa.test.pack.spacepar.Bug();
		bug.setSubject( "Spaces in directory name don't play well on Windows" );
		em.getTransaction().begin();
		em.persist( bug );
		em.flush();
		em.remove( bug );
		assertNotNull( bug.getId() );
		em.getTransaction().rollback();
		em.close();
		emf.close();
	}
