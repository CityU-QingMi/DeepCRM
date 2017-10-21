	protected void doTest() {
		setup();

		for ( int i = 0; i < NUMBER_UPDATES; i++ ) {
			newEntityManager();
			EntityManager entityManager = getEntityManager();

			entityManager.getTransaction().begin();
			Integer id = ids.get( random.nextInt( NUMBER_ENTITIES ) );
			start();
			StrTestEntity testEntity = entityManager.find( StrTestEntity.class, id );
			testEntity.setStr( "z" + i );
			entityManager.getTransaction().commit();
			stop();
		}
	}
