	@Before
	public void setUp() {
		EntityManager em = createEntityManager();
		try {
			em.getTransaction().begin();

			for ( int i = 0; i < 5; i++ ) {
				TestEntity e = new TestEntity();
				EntityA eA = new EntityA();
				eA.setParent( e );
				eA.valueA = "a_" + i;

				em.persist( e );
			}
			for ( int i = 0; i < 5; i++ ) {
				TestEntity e = new TestEntity();

				EntityB eB = new EntityB();
				eB.valueB = "b_" + i;
				eB.setParent( e );

				em.persist( e );
			}

			em.getTransaction().commit();
		}
		catch (Exception e) {
			if ( em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			em.close();
		}
	}
