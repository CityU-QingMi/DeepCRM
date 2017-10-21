	@Test
	public void testAliasWithNullOrNonNullRestrictions() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria( Order.class );
		// create alias, ensuring we stay on the root criteria
		assertSame( rootCriteria, rootCriteria.createAlias( "orderLines", "ol", JoinFragment.LEFT_OUTER_JOIN ) );

		// add restrictions to rootCriteria
		assertSame(
				rootCriteria,
				rootCriteria.add(
						Restrictions.or(
								Restrictions.isNull( "ol.articleId" ),		  // Allow null
								Restrictions.eq( "ol.articleId", "1000" )
						)
				)
		);

		List orders = rootCriteria.list();

		// order1 should be returned because it has an orderline with articleId == "1000";
		// the contained collection for order1 should only have the orderLine with articleId == "1000";
		// order2 should be returned because it has no orderlines
		assertEquals( 2, orders.size() );
		for ( Object order : orders ) {
			Order o = (Order) order;
			if ( order1.getOrderId() == o.getOrderId() ) {
				assertEquals( "1000", o.getLines().iterator().next().getArticleId() );
			}
			else if ( order2.getOrderId() == o.getOrderId() ) {
				assertEquals( 0, o.getLines().size() );
			}
			else {
				fail( "unknown order" );
			}
		}
		s.getTransaction().commit();
		s.close();
	}
