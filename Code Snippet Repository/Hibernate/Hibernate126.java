	@Test
	public void test_hql_select_clause_dynamic_map_instantiation_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-select-clause-dynamic-map-instantiation-example[]
			List<Map> phoneCallTotalDurations = entityManager.createQuery(
				"select new map(" +
				"	p.number as phoneNumber , " +
				"	sum(c.duration) as totalDuration, " +
				"	avg(c.duration) as averageDuration " +
				")  " +
				"from Call c " +
				"join c.phone p " +
				"group by p.number ", Map.class )
			.getResultList();
			//end::hql-select-clause-dynamic-map-instantiation-example[]
			assertNotNull(phoneCallTotalDurations);
		});
	}
