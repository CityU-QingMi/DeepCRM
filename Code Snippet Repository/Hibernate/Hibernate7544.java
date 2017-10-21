	@Test
	public void testSubcriteriaWithNonNullRestrictions() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria( Order.class );
		Criteria subCriteria = rootCriteria.createCriteria( "orderLines", JoinFragment.LEFT_OUTER_JOIN );
		assertNotSame( rootCriteria, subCriteria );

		// add restrictions to subCriteria, ensuring we stay on subCriteria
		assertSame( subCriteria, subCriteria.add( Restrictions.eq( "articleId", "3000" ) ) );

		List orders = rootCriteria.list();

		// order1 and order3 should be returned because each has articleId == "3000"
		// both should have their full collection
		assertEquals( 2, orders.size() );
		for ( Iterator it = orders.iterator(); it.hasNext(); ) {
			Order o = (Order) it.next();
			if ( order1.getOrderId() == o.getOrderId() ) {
				assertEquals( order1.getLines().size(), o.getLines().size() );
			}
			else if ( order3.getOrderId() == o.getOrderId() ) {
				assertEquals( order3.getLines().size(), o.getLines().size() );
			}
			else {
				fail( "unknown order" );
			}
		}
		s.getTransaction().commit();
		s.close();
	}
