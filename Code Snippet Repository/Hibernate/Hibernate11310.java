	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		em.persist(
				new ImplicitTransitiveChildEntity(
						childImpTransId,
						"grandparent 1",
						"notAudited 1",
						"parent 1",
						"child 1"
				)
		);
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		em.persist(
				new ExplicitTransitiveChildEntity(
						childExpTransId,
						"grandparent 2",
						"notAudited 2",
						"parent 2",
						"child 2"
				)
		);
		em.getTransaction().commit();
	}
