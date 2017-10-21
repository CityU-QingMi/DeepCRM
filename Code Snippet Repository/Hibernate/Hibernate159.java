	@Test
	@RequiresDialect(H2Dialect.class)
	@RequiresDialect(PostgreSQL81Dialect.class)
	@RequiresDialect(MySQL5Dialect.class)
	public void test_hql_group_by_example_3() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-group-by-example[]

			//It's even possible to group by entities!
			List<Object[]> personTotalCallDurations = entityManager.createQuery(
				"select p, sum( c.duration ) " +
				"from Call c " +
				"join c.phone ph " +
				"join ph.person p " +
				"group by p", Object[].class )
			.getResultList();
			//end::hql-group-by-example[]
			assertEquals(1, personTotalCallDurations.size());
		});
	}
