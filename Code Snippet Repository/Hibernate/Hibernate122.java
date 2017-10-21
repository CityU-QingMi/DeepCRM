	@Test
	public void test_hql_null_if_example_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-nullif-example[]
			List<String> nickNames = entityManager.createQuery(
				"select nullif( p.nickName, p.name ) " +
				"from Person p", String.class )
			.getResultList();
			//end::hql-nullif-example[]
			assertEquals(3, nickNames.size());
		});
	}
