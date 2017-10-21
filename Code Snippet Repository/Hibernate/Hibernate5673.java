	@Test
	public void testParameterReuse() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Product> criteria = em.getCriteriaBuilder().createQuery( Product.class );
		Root<Product> from = criteria.from( Product.class );
		ParameterExpression<String> param = em.getCriteriaBuilder().parameter( String.class );
		Predicate predicate = em.getCriteriaBuilder().equal( from.get( Product_.id ), param );
		Predicate predicate2 = em.getCriteriaBuilder().equal( from.get( Product_.name ), param );
		criteria.where( em.getCriteriaBuilder().or( predicate, predicate2 ) );
		assertEquals( 1, criteria.getParameters().size() );
		TypedQuery<Product> query = em.createQuery( criteria );
		int hqlParamCount = countGeneratedParameters( query.unwrap( Query.class ) );
		assertEquals( 1, hqlParamCount );
		query.setParameter( param, "abc" ).getResultList();
		em.getTransaction().commit();
		em.close();
	}
