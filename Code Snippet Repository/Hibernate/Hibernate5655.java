	@Test
	public void testSumOfBigDecimals() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<BigDecimal> criteria = builder.createQuery( BigDecimal.class );
		Root<Product> productRoot = criteria.from( Product.class );
		criteria.select( builder.sum( productRoot.get( Product_.someBigDecimal ) ) );
		Object sumResult = em.createQuery( criteria ).getSingleResult();
		assertReturnType( BigDecimal.class, sumResult );
		em.getTransaction().commit();
		em.close();
	}
