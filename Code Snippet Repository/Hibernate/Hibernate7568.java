	@Test
	public void testJoinTable() {
		Seller[] sellers = persist( "join-table" );
		Seller seller1 = sellers[0];
		Seller seller2 = sellers[1];

		Session session = openSession();

		Criteria criteria = session.createCriteria( Seller.class, "s" );
		criteria.createCriteria(
				"s.soldTo",
				"c",
				JoinType.INNER_JOIN,
				Restrictions.eq( "name", "join-table-customer1" )
		);
		criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
		@SuppressWarnings("unchecked")
		List<Seller> results = criteria.list();
		assertTrue( results.size() == 1 );
		assertTrue( results.contains( seller1 ) );
		assertFalse( results.contains( seller2 ) );


		criteria = session.createCriteria( Seller.class, "s" );
		criteria.createCriteria(
				"s.soldTo",
				"c",
				JoinType.INNER_JOIN,
				Restrictions.eq( "name", "join-table-customer2" )
		);
		criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );

		@SuppressWarnings("unchecked")
		List<Seller> results2 = criteria.list();
		assertTrue( results2.size() == 2 );
		assertTrue( results2.contains( seller1 ) );
		assertTrue( results2.contains( seller2 ) );

		session.close();
	}
