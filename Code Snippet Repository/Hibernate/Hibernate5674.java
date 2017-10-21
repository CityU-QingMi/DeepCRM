	@Test
	public void testInExplicitTupleList() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Product> criteria = builder.createQuery( Product.class );
		Root<Product> from = criteria.from( Product.class );
		criteria.where( from.get( Product_.partNumber ).in( Collections.singletonList( ((long)Integer.MAX_VALUE) + 1 ) ) );
		List<Product> result = em.createQuery( criteria ).getResultList();
		assertEquals( 1, result.size() );
		em.getTransaction().commit();
		em.close();
	}
