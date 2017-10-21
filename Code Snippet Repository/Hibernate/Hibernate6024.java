	@Before
	public void setUp() {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery( "CREATE ALIAS get_all_entities FOR \"" + H2StoreProcedureTest.class.getCanonicalName() + ".getAllEntities\";" )
					.executeUpdate();

			entityManager.createNativeQuery( "CREATE ALIAS by_id FOR \"" + H2StoreProcedureTest.class.getCanonicalName() + ".entityById\";" )
					.executeUpdate();
			MyEntity entity = new MyEntity();
			entity.id = 1;
			entity.name = "entity1";
			entityManager.persist( entity );

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
