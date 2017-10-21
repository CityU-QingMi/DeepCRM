	@Test
	public void test_hql_collection_expressions_example_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Call call = entityManager.createQuery( "select c from Call c", Call.class).getResultList().get( 0 );
			//tag::hql-collection-expressions-example[]

			List<Phone> phones = entityManager.createQuery(
				"select p " +
				"from Phone p " +
				"where minelement( p.calls ) = :call", Phone.class )
			.setParameter( "call", call )
			.getResultList();
			//end::hql-collection-expressions-example[]
			assertEquals(1, phones.size());
		});
	}
