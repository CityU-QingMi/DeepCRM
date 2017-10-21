	@Test
	public void testExplicitBuilderBooleanHandling() {
		// just checking syntax of the resulting query
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		// note : these may fail on various matrix db jobs depending on how the dialect handles booleans
		{
			CriteriaQuery<CreditCard> criteriaQuery = builder.createQuery( CreditCard.class );
			Root<CreditCard> root = criteriaQuery.from( CreditCard.class );
			criteriaQuery.where( builder.isFalse( root.get( CreditCard_.approved ) ) );
			em.createQuery( criteriaQuery ).getResultList();
		}

		{
			CriteriaQuery<Order> criteriaQuery = builder.createQuery( Order.class );
			Root<Order> root = criteriaQuery.from( Order.class );
			criteriaQuery.where( builder.isFalse( root.get( Order_.creditCard ).get( CreditCard_.approved ) ) );
			em.createQuery( criteriaQuery ).getResultList();
		}

		em.getTransaction().commit();
		em.close();
	}
