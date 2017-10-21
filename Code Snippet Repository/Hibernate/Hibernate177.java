	@Test
	public void test_hql_collection_qualification_associations_5() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			Long id = 1L;
			Integer phoneIndex = 0;
			//tag::hql-collection-qualification-example[]

			// Sum all call durations for a given Phone of a specific Person
			Long duration = entityManager.createQuery(
				"select sum(ch.duration) " +
				"from Person pr " +
				"join pr.phones ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id " +
				"  and index(ph) = :phoneIndex", Long.class )
			.setParameter( "id", id )
			.setParameter( "phoneIndex", phoneIndex )
			.getSingleResult();
			//end::hql-collection-qualification-example[]
			assertEquals(45, duration.intValue());

		});
	}
