	@Test
	public void test_hql_select_clause_dynamic_list_instantiation_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-select-clause-dynamic-list-instantiation-example[]
			List<List> phoneCallDurations = entityManager.createQuery(
				"select new list(" +
				"	p.number, " +
				"	c.duration " +
				")  " +
				"from Call c " +
				"join c.phone p ", List.class )
			.getResultList();
			//end::hql-select-clause-dynamic-list-instantiation-example[]
			assertNotNull(phoneCallDurations);
		});
	}
