	@Test
	public void test_hql_polymorphism_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-polymorphism-example[]
			List<Payment> payments = entityManager.createQuery(
				"select p " +
				"from Payment p ", Payment.class )
			.getResultList();
			//end::hql-polymorphism-example[]
			assertEquals(2, payments.size());
		});
	}
