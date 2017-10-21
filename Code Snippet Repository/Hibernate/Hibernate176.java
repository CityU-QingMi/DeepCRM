	@Test
	public void test_hql_collection_qualification_associations_4() {
	try {
		doInJPA( this::entityManagerFactory, entityManager -> {

			Long id = 1L;
			//tag::hql-collection-qualification-example[]

			// select all the Call and their timestamps (the 'Map.Entry') for a given Phone
			List<Map.Entry<Date, Call>> callHistory = entityManager.createQuery(
				"select entry(ch) " +
				"from Phone ph " +
				"join ph.callHistory ch " +
				"where ph.id = :id " )
			.setParameter( "id", id )
			.getResultList();
			//end::hql-collection-qualification-example[]

		});
	} catch(Exception e) {
		//@see https://hibernate.atlassian.net/browse/HHH-10491
	}
	}
