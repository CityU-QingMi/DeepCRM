	@Test
	public void test_hql_collection_qualification_associations_3() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Long id = 1L;
			//tag::hql-collection-qualification-example[]

			// select all the Call timestamps (the map key) for a given Phone
			List<Date> timestamps = entityManager.createQuery(
				"select key(ch) " +
				"from Phone ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id ", Date.class )
			.setParameter( "id", id )
			.getResultList();
			//end::hql-collection-qualification-example[]
			assertEquals(2, timestamps.size());
		});
	}
