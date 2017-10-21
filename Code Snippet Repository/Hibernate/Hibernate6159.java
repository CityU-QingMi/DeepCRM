	@Test(expected = PersistenceException.class)
	@TestForIssue(jiraKey = "")
	public void deserializedEntityManagerPersistenceExceptionManagementTest() throws IOException {
		EntityManager entityManager = getOrCreateEntityManager();
		final EntityManager deserializedSession = spoofSerialization( entityManager );
		try {
			deserializedSession.getTransaction().begin();
			TestEntity entity = new TestEntity();
			entity.setName( "Andrea" );
			deserializedSession.persist( entity );
			entity.setName( null );
			deserializedSession.flush();
		}
		finally {
			deserializedSession.getTransaction().rollback();
			deserializedSession.close();
			entityManager.close();
		}
	}
