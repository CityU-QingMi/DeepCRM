	@Test
	@TestForIssue( jiraKey = "")
	public void testLoadGetId() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Session s = ( Session ) em.getDelegate();
		Workload workload = new Workload();
		s.persist(workload);
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		s = ( Session ) em.getDelegate();

		Workload proxy = s.load(Workload.class, workload.id);
		proxy.getId();

		assertFalse( Hibernate.isInitialized( proxy ) );

		proxy.getName();

		assertTrue( Hibernate.isInitialized( proxy ) );

		em.getTransaction().commit();
		em.close();
	}
