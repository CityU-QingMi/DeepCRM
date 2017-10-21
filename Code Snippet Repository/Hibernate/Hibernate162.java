	@Test
	public void test_hql_order_by_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-order-by-example[]
			List<Person> persons = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"order by p.name", Person.class )
			.getResultList();
			//end::hql-order-by-example[]
			assertEquals(3, persons.size());
		});
	}
