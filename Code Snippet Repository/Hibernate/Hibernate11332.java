	protected void doTest() {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		start();
		for ( int i = 0; i < NUMBER_INSERTS; i++ ) {
			entityManager.persist( new StrTestEntity( "x" + i ) );
		}
		entityManager.getTransaction().commit();
		stop();
	}
