	@Test
	public void test_hql_relational_comparisons_example_4() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-relational-comparisons-example[]

			// enum comparison
			List<Phone> phones = entityManager.createQuery(
				"select p " +
				"from Phone p " +
				"where p.type = 'MOBILE' ", Phone.class )
			.getResultList();
			//end::hql-relational-comparisons-example[]
			assertEquals(1, phones.size());
		});
	}
