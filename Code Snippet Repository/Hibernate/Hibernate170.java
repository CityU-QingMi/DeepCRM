	@Test
	public void test_hql_collection_valued_associations_1() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			String address = "Earth";
			int duration = 20;
			//tag::hql-collection-valued-associations[]
			List<Phone> phones = entityManager.createQuery(
				"select ph " +
				"from Person pr " +
				"join pr.phones ph " +
				"join ph.calls c " +
				"where pr.address = :address " +
				"  and c.duration > :duration", Phone.class )
			.setParameter( "address", address )
			.setParameter( "duration", duration )
			.getResultList();
			//end::hql-collection-valued-associations[]
			assertEquals(1, phones.size());
		});

	}
