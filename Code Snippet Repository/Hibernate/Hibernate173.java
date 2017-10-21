	@Test
	public void test_hql_collection_qualification_associations_1() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			Long id = 1L;
			//tag::hql-collection-qualification-example[]

			// select all the calls (the map value) for a given Phone
			List<Call> calls = entityManager.createQuery(
				"select ch " +
				"from Phone ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id ", Call.class )
			.setParameter( "id", id )
			.getResultList();
			//end::hql-collection-qualification-example[]
			assertEquals(2, calls.size());
		});

	}
