	@Test
	public void testSumOfBigIntegers() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<BigInteger> criteria = builder.createQuery( BigInteger.class );
		Root<Product> productRoot = criteria.from( Product.class );
		criteria.select( builder.sum( productRoot.get( Product_.someBigInteger ) ) );
		Object sumResult = em.createQuery( criteria ).getSingleResult();
		assertReturnType( BigInteger.class, sumResult );
		em.getTransaction().commit();
		em.close();
	}
