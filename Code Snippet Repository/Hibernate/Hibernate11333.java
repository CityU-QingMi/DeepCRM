	protected void doTest() {
		for ( int i = 0; i < NUMBER_INSERTS; i++ ) {
			newEntityManager();
			EntityManager entityManager = getEntityManager();

			entityManager.getTransaction().begin();
			start();
			entityManager.persist( new StrTestEntity( "x" + i ) );
			entityManager.getTransaction().commit();
			stop();
		}
	}
