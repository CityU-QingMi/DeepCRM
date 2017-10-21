	@Test
	public void test_hql_relational_comparisons_example_7() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-relational-comparisons-example[]

			// entity value comparison
			List<Object[]> phonePayments = entityManager.createQuery(
				"select p " +
				"from Payment p, Phone ph " +
				"where p.person = ph.person ", Object[].class )
			.getResultList();
			//end::hql-relational-comparisons-example[]
			assertEquals(3, phonePayments.size());
		});
	}
