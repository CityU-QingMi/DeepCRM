	@Test
	public void testFunctionDialectFunctions() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaBuilderImpl cb = (CriteriaBuilderImpl) em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = cb.createQuery( Long.class );
		criteria.select( cb.count( cb.literal( 1 ) ) );
		Root<Customer> root = criteria.from( Customer.class );
		criteria.where(
				cb.equal(
						cb.function(
								"substring",
								String.class,
								root.get( Customer_.name ),
								cb.literal( 1 ),
								cb.literal( 1 )
						),
						cb.literal( "a" )
				)
		);
		em.createQuery( criteria ).getResultList();
		em.getTransaction().commit();
		em.close();
	}
