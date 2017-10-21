	@Test
	public void testJpaEnvers() throws Exception {
		final ServiceReference serviceReference = bundleContext.getServiceReference( PersistenceProvider.class.getName() );
		final PersistenceProvider persistenceProvider = (PersistenceProvider) bundleContext.getService( serviceReference );
		final EntityManagerFactory emf = persistenceProvider.createEntityManagerFactory( "hibernate-osgi-test", null );

		final Integer adpId;

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		AuditedDataPoint adp = new AuditedDataPoint( "Chris" );
		em.persist( adp );
		em.getTransaction().commit();
		adpId = adp.getId();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		adp = em.find( AuditedDataPoint.class, adpId );
		adp.setName( "Chris2" );
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		AuditReader ar = AuditReaderFactory.get( em );
		assertEquals( 2, ar.getRevisions( AuditedDataPoint.class, adpId ).size() );
		AuditedDataPoint rev1 = ar.find( AuditedDataPoint.class, adpId, 1 );
		AuditedDataPoint rev2 = ar.find( AuditedDataPoint.class, adpId, 2 );
		assertEquals( new AuditedDataPoint( adpId, "Chris" ), rev1 );
		assertEquals( new AuditedDataPoint( adpId, "Chris2" ), rev2 );
		em.close();
	}
