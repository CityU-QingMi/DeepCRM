	@Test
	public void testSubcriteriaWithNonNullRestrictionsAliasToEntityMap() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria( Order.class, "o" );
		Criteria subCriteria = rootCriteria.createCriteria( "orderLines", "ol", JoinFragment.LEFT_OUTER_JOIN );
		assertNotSame( rootCriteria, subCriteria );

		// add restriction to subCriteria, ensuring we stay on subCriteria
		assertSame( subCriteria, subCriteria.add( Restrictions.eq( "articleId", "3000" ) ) );

		List orders = rootCriteria.setResultTransformer( Criteria.ALIAS_TO_ENTITY_MAP ).list();

		// order1 and order3 should be returned because each has articleId == "3000";
		// the orders should both should have their full collection;
		assertEquals( 2, orders.size() );
		for ( Iterator it = orders.iterator(); it.hasNext(); ) {
			Map map = (Map) it.next();
			Order o = ( Order ) map.get( "o" );
		    // the orderLine returned from the map should have articleId = "3000"
			OrderLine ol = ( OrderLine ) map.get( "ol" );
			if ( order1.getOrderId() == o.getOrderId() ) {
				assertEquals( order1.getLines().size(), o.getLines().size() );
				assertEquals( "3000", ol.getArticleId() );
			}
			else if ( order3.getOrderId() == o.getOrderId() ) {
				assertEquals( order3.getLines().size(), o.getLines().size() );
				assertEquals( "3000", ol.getArticleId() );
			}
			else {
				fail( "unknown order" );
			}
		}
		s.getTransaction().commit();
		s.close();
	}
