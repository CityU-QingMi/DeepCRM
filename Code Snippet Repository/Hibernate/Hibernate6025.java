	@After
	public void tearDown() {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery( "DROP ALIAS IF EXISTS get_all_entities" ).executeUpdate();
			entityManager.createNativeQuery( "DROP ALIAS IF EXISTS by_id" ).executeUpdate();
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
