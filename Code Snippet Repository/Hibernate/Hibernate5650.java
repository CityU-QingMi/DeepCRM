	@Test
	public void testSumOfLongs() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Long> criteria = builder.createQuery( Long.class );
		Root<Product> productRoot = criteria.from( Product.class );
		criteria.select( builder.sum( productRoot.get( Product_.partNumber ) ) );
		Object sumResult = em.createQuery( criteria ).getSingleResult();
		assertReturnType( Long.class, sumResult );
		em.getTransaction().commit();
		em.close();
	}
