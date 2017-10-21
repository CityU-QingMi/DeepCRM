	@Test
	public void test_hql_mod_function_example() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-mod-function-example[]
			List<Integer> mods = entityManager.createQuery(
				"select mod( c.duration, 10 ) " +
				"from Call c ", Integer.class )
			.getResultList();
			//end::hql-mod-function-example[]
			assertEquals(2, mods.size());
		});
	}
