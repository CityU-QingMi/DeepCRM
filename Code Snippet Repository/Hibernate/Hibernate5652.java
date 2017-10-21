	@Test
	public void testSumOfDoubles() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Double> criteria = builder.createQuery( Double.class );
		Root<Product> productRoot = criteria.from( Product.class );
		criteria.select( builder.sum( productRoot.get( Product_.price ) ) );
		Object sumResult = em.createQuery( criteria ).getSingleResult();
		assertReturnType( Double.class, sumResult );
		em.getTransaction().commit();
		em.close();
	}
