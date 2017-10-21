	@Test
	public void testNonNullSubcriteriaRestrictionsOnRootCriteria() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria( Order.class );
		Criteria subCriteria = rootCriteria.createCriteria( "orderLines", "ol", JoinFragment.LEFT_OUTER_JOIN );
		assertNotSame( rootCriteria, subCriteria );

		// add restriction to rootCriteria (NOT subcriteria)
		assertSame( rootCriteria, rootCriteria.add( Restrictions.eq( "ol.articleId", "3000" ) ) );

		List orders = rootCriteria.list();

		// results should be the same as testAliasWithNonNullRestrictions() (using Criteria.createAlias())
		// order1 and order3 should be returned because each has articleId == "3000"
		// the contained collections should only have the orderLine with articleId == "3000"
		assertEquals( 2, orders.size() );
		for ( Iterator it = orders.iterator(); it.hasNext(); ) {
			Order o = (Order) it.next();
			if ( order1.getOrderId() == o.getOrderId() ) {
				assertEquals( 1, o.getLines().size() );
				assertEquals( "3000", o.getLines().iterator().next().getArticleId() );
			}
			else if ( order3.getOrderId() == o.getOrderId() ) {
				assertEquals( 1, o.getLines().size() );
				assertEquals( "3000", o.getLines().iterator().next().getArticleId() );
			}
			else {
				fail( "unknown order" );
			}
		}
		s.getTransaction().commit();
		s.close();
	}
