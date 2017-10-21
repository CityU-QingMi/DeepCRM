	@Before
	public void prepareTestData() {
		builder = entityManagerFactory().getCriteriaBuilder();

		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		em.persist( new Order( "order-1", 1.0d ) );
		em.persist( new Order( "order-2", 10.0d ) );
		em.persist( new Order( "order-3", new char[]{'r','u'} ) );
		em.getTransaction().commit();
		em.close();
	}
