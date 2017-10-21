	@Test
	public void test_hql_collection_qualification_associations_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Long id = 1L;
			//tag::hql-collection-qualification-example[]

			// same as above
			List<Call> calls = entityManager.createQuery(
				"select value(ch) " +
				"from Phone ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id ", Call.class )
			.setParameter( "id", id )
			.getResultList();
			//end::hql-collection-qualification-example[]
			assertEquals(2, calls.size());
		});
	}
