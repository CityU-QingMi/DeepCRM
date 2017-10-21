	@Test
	public void test_hql_entity_type_exp_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-entity-type-exp-example[]
			List<Payment> payments = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where type(p) = CreditCardPayment", Payment.class )
			.getResultList();
			//end::hql-entity-type-exp-example[]
			assertEquals(1, payments.size());
		});
	}
