	@Test
	public void test_hql_numeric_arithmetic_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-numeric-arithmetic-example[]
			// select clause date/time arithmetic operations
			Long duration = entityManager.createQuery(
				"select sum(ch.duration) * :multiplier " +
				"from Person pr " +
				"join pr.phones ph " +
				"join ph.callHistory ch " +
				"where ph.id = 1L ", Long.class )
			.setParameter( "multiplier", 1000L )
			.getSingleResult();
			//end::hql-numeric-arithmetic-example[]
			assertTrue(duration > 0);
		});
	}
