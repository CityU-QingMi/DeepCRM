	@Test
	public void test_hql_collection_expressions_example_8() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-collection-expressions-example[]

			List<Phone> phones = entityManager.createQuery(
				"select p " +
				"from Phone p " +
				"where current_date() > all elements( p.repairTimestamps )", Phone.class )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(3, phones.size());
		});
	}
