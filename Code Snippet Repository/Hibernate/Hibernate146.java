	@Test
	public void test_hql_in_predicate_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-in-predicate-example[]
			List<Payment> payments = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where type(p) in ( CreditCardPayment, WireTransferPayment )", Payment.class )
			.getResultList();
			//end::hql-in-predicate-example[]
			assertEquals(2, payments.size());
		});
	}
