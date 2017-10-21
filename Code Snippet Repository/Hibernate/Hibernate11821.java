	@Test
	public void testJpa() throws Exception {
		final ServiceReference serviceReference = bundleContext.getServiceReference( PersistenceProvider.class.getName() );
		final PersistenceProvider persistenceProvider = (PersistenceProvider) bundleContext.getService( serviceReference );
		final EntityManagerFactory emf = persistenceProvider.createEntityManagerFactory( "hibernate-osgi-test", null );

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist( new DataPoint( "Brett" ) );
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		DataPoint dp = em.find( DataPoint.class, 1 );
		assertNotNull( dp );
		assertEquals( "Brett", dp.getName() );
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		dp = em.find( DataPoint.class, 1 );
		dp.setName( "Brett2" );
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete from DataPoint" ).executeUpdate();
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		dp = em.find( DataPoint.class, 1 );
		assertNull( dp );
		em.getTransaction().commit();
		em.close();
	}
