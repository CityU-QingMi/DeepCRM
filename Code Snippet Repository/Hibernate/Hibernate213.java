	@Test
	public void test_hql_concatenation_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-concatenation-example[]
			String name = entityManager.createQuery(
				"select 'Customer ' || p.name " +
				"from Person p " +
				"where p.id = 1", String.class )
			.getSingleResult();
			//end::hql-concatenation-example[]
			assertNotNull(name);
		});
	}
