	@Before
	public void setUp() {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		try {
			TestEntity testEntity = new TestEntity();
			testEntity.setName( "test_1" );
			entityManager.persist( testEntity );
			entityManager.getTransaction().commit();
		}
		catch (Exception e) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			entityManager.close();
		}
	}
