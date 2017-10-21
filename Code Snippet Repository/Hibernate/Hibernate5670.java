	@Test
	public void testDiffWithQuotient() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Number> criteria = builder.createQuery( Number.class );
		criteria.from( Product.class );
		criteria.select(
				builder.quot(
						builder.diff(
								builder.literal( BigDecimal.valueOf( 2.0 ) ),
								builder.literal( BigDecimal.valueOf( 1.0 ) )
						),
						BigDecimal.valueOf( 2.0 )
				)
		);
		Number result = em.createQuery( criteria ).getSingleResult();
		assertEquals(0.5d, result.doubleValue(), 0.1d);
		em.getTransaction().commit();
		em.close();
	}
