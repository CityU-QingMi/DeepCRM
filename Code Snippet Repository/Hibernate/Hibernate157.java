	@Test
	public void test_hql_group_by_example_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-group-by-example[]
			Long totalDuration = entityManager.createQuery(
				"select sum( c.duration ) " +
				"from Call c ", Long.class )
			.getSingleResult();
			//end::hql-group-by-example[]
			assertEquals(Long.valueOf( 45 ), totalDuration);
		});
	}
