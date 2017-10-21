	@Test
	public void test_hql_relational_comparisons_example_6() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-relational-comparisons-example[]

			// boolean comparison
			List<Payment> payments = entityManager.createQuery(
				"select p " +
				"from Payment p " +
				"where type(p) = WireTransferPayment ", Payment.class )
			.getResultList();
			//end::hql-relational-comparisons-example[]
			assertEquals(1, payments.size());
		});
	}
