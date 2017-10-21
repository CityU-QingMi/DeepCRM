	@Test
	public void testQuotientAndMultiply() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Number> criteria = builder.createQuery( Number.class );
		criteria.from( Product.class );
		criteria.select(
				builder.quot(
						builder.prod(
								builder.literal( BigDecimal.valueOf( 10.0 ) ),
								builder.literal( BigDecimal.valueOf( 5.0 ) )
						),
						BigDecimal.valueOf( 2.0 )
				)
		);
		Number result = em.createQuery( criteria ).getSingleResult();
		assertEquals(25.0d, result.doubleValue(), 0.1d);

		criteria.select(
				builder.prod(
						builder.quot(
								builder.literal( BigDecimal.valueOf( 10.0 ) ),
								builder.literal( BigDecimal.valueOf( 5.0 ) )
						),
						BigDecimal.valueOf( 2.0 )
				)
		);
		result = em.createQuery( criteria ).getSingleResult();
		assertEquals(4.0d, result.doubleValue(), 0.1d);

		em.getTransaction().commit();
		em.close();
	}
