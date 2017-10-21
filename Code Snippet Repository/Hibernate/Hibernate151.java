	@Test
	@RequiresDialectFeature(DialectChecks.SupportRowValueConstructorSyntaxInInList.class)
	public void test_hql_in_predicate_example_6() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-in-predicate-example[]

			// Not JPQL compliant!
			List<Payment> payments = entityManager.createQuery(
				"select distinct p " +
				"from Payment p " +
				"where ( p.amount, p.completed ) in (" +
				"	(50, true )," +
				"	(100, true )," +
				"	(5, false )" +
				")", Payment.class )
			.getResultList();
			//end::hql-in-predicate-example[]
			assertEquals(1, payments.size());
		});
	}
