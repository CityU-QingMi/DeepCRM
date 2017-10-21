	@Test
	public void testSubcriteriaWithNullOrNonNullRestrictions() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria( Order.class );
		Criteria subCriteria = rootCriteria.createCriteria( "orderLines", JoinFragment.LEFT_OUTER_JOIN );
		assertNotSame( rootCriteria, subCriteria );

		// add restrictions to subCriteria, ensuring we stay on subCriteria
		// add restriction to subCriteria, ensuring we stay on subCriteria
		assertSame(
				subCriteria,
				subCriteria.add(
					Restrictions.or(
							Restrictions.isNull( "articleId" ),		  // Allow null
							Restrictions.eq( "articleId", "1000" )
					)
				)
		);

		List orders = rootCriteria.list();

		// order1 should be returned because it has an orderline with articleId == "1000";
		// order2 should be returned because it has no orderlines
		assertEquals( 2, orders.size() );
		for ( Iterator it = orders.iterator(); it.hasNext(); ) {
			Order o = ( Order ) it.next();
			if ( order1.getOrderId() == o.getOrderId() ) {
				// o.getLines() should contain all of its orderLines
				assertEquals( order1.getLines().size(), o.getLines().size() );
			}
			else if ( order2.getOrderId() == o.getOrderId() ) {
				assertEquals( order2.getLines() , o.getLines() );
				assertTrue( o.getLines().isEmpty() );
			}
			else {
				fail( "unknown order" );
			}
		}
		s.getTransaction().commit();
		s.close();
	}
