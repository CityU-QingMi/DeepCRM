	@Test
	public void testORMFileOnMainAndExplicitJars() throws Exception {
		File testPackage = buildExplicitPar();
		addPackageToClasspath( testPackage );

		emf = Persistence.createEntityManagerFactory( "manager1", new HashMap() );
		EntityManager em = emf.createEntityManager();
		Seat seat = new Seat();
		seat.setNumber( "3B" );
		Airplane plane = new Airplane();
		plane.setSerialNumber( "75924418409052355" );
		em.getTransaction().begin();
		em.persist( seat );
		em.persist( plane );
		em.flush();
		em.getTransaction().rollback();
		em.close();
		emf.close();
	}
