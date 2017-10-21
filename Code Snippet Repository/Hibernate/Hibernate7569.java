	@Test
	public void testMappedBy() {
		Set<Customer> customersAll = new LinkedHashSet<Customer>();
		Seller[] sellers = persist( "mappedby" );
		customersAll.addAll( sellers[0].getSoldTo() );
		customersAll.addAll( sellers[1].getSoldTo() );

		Customer[] customers = customersAll.toArray( new Customer[customersAll.size()] );
		Customer customer1 = customers[0];
		Customer customer2 = customers[1];
		Customer customer3 = customers[2];

		Session session = openSession();

		Criteria criteria = session.createCriteria( Customer.class, "c" );
		criteria.createCriteria(
				"c.boughtFrom",
				"s",
				JoinType.INNER_JOIN,
				Restrictions.eq( "name", "mappedby-seller1" )
		);
		criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
		@SuppressWarnings("unchecked")
		List<Customer> results = criteria.list();
		assertTrue( results.size() == 2 );
		assertTrue( results.contains( customer1 ) );
		assertTrue( results.contains( customer2 ) );
		assertFalse( results.contains( customer3 ) );


		criteria = session.createCriteria( Customer.class, "c" );
		criteria.createCriteria(
				"c.boughtFrom",
				"s",
				JoinType.INNER_JOIN,
				Restrictions.eq( "name", "mappedby-seller2" )
		);
		criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
		@SuppressWarnings("unchecked")
		List<Customer> results2 = criteria.list();
		assertTrue( results2.size() == 2 );
		assertFalse( results2.contains( customer1 ) );
		assertTrue( results2.contains( customer2 ) );
		assertTrue( results2.contains( customer3 ) );

		session.close();
	}
