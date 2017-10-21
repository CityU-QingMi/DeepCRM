	@Test
	public void testImplicitJoinFromExplicitCollectionJoin() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Entity1> criteria = builder.createQuery(Entity1.class);

		final Root<Entity1> root = criteria.from(Entity1.class);
		final Join<Entity1, Entity2> entity2Join = root.join( Entity1_.entity2, JoinType.INNER); // illegal with fetch join

		final Fetch<Entity1, Entity2> entity2Fetch = root.fetch(Entity1_.entity2, JoinType.INNER); // <=== REMOVE
		entity2Fetch.fetch( Entity2_.entity3 ); // <=== REMOVE

		criteria.where(builder.equal(root.get(Entity1_.value), "test"),
				builder.equal(entity2Join.get(Entity2_.value), "test")); // illegal with fetch join

		em.createQuery(criteria).getResultList();

		em.getTransaction().commit();
		em.close();
	}
