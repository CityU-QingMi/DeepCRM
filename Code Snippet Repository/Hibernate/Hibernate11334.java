	private void setup() {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();

		for ( int i = 0; i < NUMBER_ENTITIES; i++ ) {
			StrTestEntity testEntity = new StrTestEntity( "x" + i );
			entityManager.persist( testEntity );
			ids.add( testEntity.getId() );
		}
		entityManager.getTransaction().commit();
	}
