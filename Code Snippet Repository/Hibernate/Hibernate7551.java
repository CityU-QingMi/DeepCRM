	@Test
	public void testSubcriteriaWithNullOrNonNullRestrictionsAliasToEntityMap() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria( Order.class, "o" );
		Criteria subCriteria = rootCriteria.createCriteria( "orderLines", "ol", JoinFragment.LEFT_OUTER_JOIN );
		assertNotSame( rootCriteria, subCriteria );

		// add restriction to subCriteria, ensuring we stay on subCriteria
		assertSame(
				subCriteria,
				subCriteria.add(
					Restrictions.or(
							Restrictions.isNull( "ol.articleId" ),		  // Allow null
							Restrictions.eq( "ol.articleId", "1000" )
					)
				)
		);

		List orders = rootCriteria.setResultTransformer( Criteria.ALIAS_TO_ENTITY_MAP ).list();

		// order1 should be returned because it has an orderline with articleId == "1000";
		// order2 should be returned because it has no orderlines
		assertEquals( 2, orders.size() );
		for ( Iterator it = orders.iterator(); it.hasNext(); ) {
			Map map = (Map) it.next();
			Order o = ( Order ) map.get( "o" );
		    // the orderLine returned from the map should either be null or have articleId = "1000"
			OrderLine ol = ( OrderLine ) map.get( "ol" );
			if ( order1.getOrderId() == o.getOrderId() ) {
				// o.getLines() should contain all of its orderLines
				assertEquals( order1.getLines().size(), o.getLines().size() );
				assertNotNull( ol );
				assertEquals( "1000", ol.getArticleId() );
			}
			else if ( order2.getOrderId() == o.getOrderId() ) {
				assertEquals( order2.getLines() , o.getLines() );
				assertTrue( o.getLines().isEmpty() );
				assertNull( ol );
			}
			else {
				fail( "unknown order" );
			}
		}
		s.getTransaction().commit();
		s.close();
	}
