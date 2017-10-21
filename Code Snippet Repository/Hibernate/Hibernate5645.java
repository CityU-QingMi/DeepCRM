	@Test
	public void testSelectSubclassPropertyFromDowncast() {
		EntityManager em = getOrCreateEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> criteria = builder.createQuery( Integer.class );
		Root<Thing> root = criteria.from( Thing.class );
		Root<ThingWithQuantity> subroot = builder.treat( root, ThingWithQuantity.class );
		criteria.select( subroot.<Integer>get( "quantity" ) );
		em.createQuery( criteria ).getResultList();
		em.close();
	}
