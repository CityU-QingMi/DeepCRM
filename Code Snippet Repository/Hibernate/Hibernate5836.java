	@Test
	public void getIdentifierTest() throws Exception {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();

		// This gives a NullPointerException right now. Look at HHH-10623 when this issue is fixed
		Serializable nestedLegacyEntityId = (Serializable) entityManager.getEntityManagerFactory()
				.getPersistenceUnitUtil().getIdentifier(createExisitingNestedLegacyEntity());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
