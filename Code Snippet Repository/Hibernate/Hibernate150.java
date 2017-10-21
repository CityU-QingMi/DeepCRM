	@Test
	public void test_hql_in_predicate_example_5() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-in-predicate-example[]

			// Not JPQL compliant!
			List<Phone> phones = entityManager.createQuery(
				"select distinct p " +
				"from Phone p " +
				"where p.person in (" +
				"	select py.person " +
				"	from Payment py" +
				"	where py.completed = true and py.amount > 50 " +
				")", Phone.class )
			.getResultList();
			//end::hql-in-predicate-example[]
			assertEquals(2, phones.size());
		});
	}
