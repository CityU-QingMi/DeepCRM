	@Test
	public void test_hql_group_by_having_example() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-group-by-having-example[]

			List<Object[]> personTotalCallDurations = entityManager.createQuery(
				"select p.name, sum( c.duration ) " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p.name " +
				"having sum( c.duration ) > 1000", Object[].class )
			.getResultList();
			//end::hql-group-by-having-example[]
			assertEquals(0, personTotalCallDurations.size());
		});
	}
