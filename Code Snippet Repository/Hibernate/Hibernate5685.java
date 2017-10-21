	@Test
	public void testDiff() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Integer> criteria = builder.createQuery( Integer.class );
		criteria.from( Product.class );
		criteria.select( builder.diff( builder.literal( 5 ), builder.literal( 2 ) ) );
		Integer result = em.createQuery( criteria ).getSingleResult();
		assertEquals( Integer.valueOf( 3 ), result );
		em.getTransaction().commit();
		em.close();
	}
