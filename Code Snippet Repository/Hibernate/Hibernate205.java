	@Test
	public void test_hql_multiple_root_reference_jpql_example() {
		 doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-multiple-root-reference-jpql-example[]
			List<Object[]> persons = entityManager.createQuery(
				"select distinct pr, ph " +
				"from Person pr, Phone ph " +
				"where ph.person = pr and ph is not null", Object[].class)
			.getResultList();
			//end::hql-multiple-root-reference-jpql-example[]
			assertEquals(3, persons.size());
		});
	}
