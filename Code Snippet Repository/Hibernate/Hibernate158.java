	@Test
	public void test_hql_group_by_example_2() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-group-by-example[]

			List<Object[]> personTotalCallDurations = entityManager.createQuery(
				"select p.name, sum( c.duration ) " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p.name", Object[].class )
			.getResultList();
			//end::hql-group-by-example[]
			assertEquals(1, personTotalCallDurations.size());
		});
	}
