	@Test
	public void test_hql_in_predicate_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-in-predicate-example[]

			List<Phone> phones = entityManager.createQuery(
				"select p " +
				"from Phone p " +
				"where type in ( 'MOBILE', 'LAND_LINE' )", Phone.class )
			.getResultList();
			//end::hql-in-predicate-example[]
			assertEquals(3, phones.size());
		});
	}
