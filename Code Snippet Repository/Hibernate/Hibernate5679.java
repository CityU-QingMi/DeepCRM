	@Test
	@TestForIssue( jiraKey = "" )
	@RequiresDialect( H2Dialect.class )
	public void testEmptyInList() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Product> criteria = builder.createQuery( Product.class );
		Root<Product> from = criteria.from( Product.class );
		criteria.where( from.get( Product_.partNumber ).in() ); // empty IN list
		List<Product> result = em.createQuery( criteria ).getResultList();
		assertEquals( 0, result.size() );
		em.getTransaction().commit();
		em.close();
	}
