	@Test
	public void test_hql_collection_expressions_example_7() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-collection-expressions-example[]

			List<Phone> phones = entityManager.createQuery(
				"select p " +
				"from Phone p " +
				"where current_date() > key( p.callHistory )", Phone.class )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(2, phones.size());
		});
	}
