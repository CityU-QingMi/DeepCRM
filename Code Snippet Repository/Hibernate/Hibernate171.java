	@Test
	public void test_hql_collection_valued_associations_2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );
			String address = "Earth";
			int duration = 20;
			//tag::hql-collection-valued-associations[]

			// alternate syntax
			List<Phone> phones = session.createQuery(
				"select pr " +
				"from Person pr, " +
				"in (pr.phones) ph, " +
				"in (ph.calls) c " +
				"where pr.address = :address " +
				"  and c.duration > :duration" )
			.setParameter( "address", address )
			.setParameter( "duration", duration )
			.list();
			//end::hql-collection-valued-associations[]
			assertEquals(1, phones.size());
		});
	}
