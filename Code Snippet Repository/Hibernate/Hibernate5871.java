	@Test
	@TestForIssue(jiraKey = "")
	public void testEntityGraphWithCollectionSubquery(){
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();

		EntityGraph<Company> entityGraph = entityManager.createEntityGraph(Company.class);
		entityGraph.addAttributeNodes("location");
		Query query = entityManager.createQuery("select c from " + Company.class.getName() + " c where c.employees IS EMPTY");
		query.setHint(QueryHints.HINT_LOADGRAPH, entityGraph);
		query.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();
	}
