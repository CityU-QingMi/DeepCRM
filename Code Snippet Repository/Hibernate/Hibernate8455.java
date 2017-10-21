	@Test
	@TestForIssue( jiraKey = "")
	public void testLoadEntityWithEagerFetchingToKeyManyToOneReferenceBackToSelf() {
		sessionFactory().getStatistics().clear();

		// long winded method name to say that this is a test specifically for HHH-2277 ;)
		// essentially we have a bidirectional association where one side of the
		// association is actually part of a composite PK.
		//
		// The way these are mapped causes the problem because both sides
		// are defined as eager which leads to the infinite loop; if only
		// one side is marked as eager, then all is ok.  In other words the
		// problem arises when both pieces of instance data are coming from
		// the same result set.  This is because no "entry" can be placed
		// into the persistence context for the association with the
		// composite key because we are in the process of trying to build
		// the composite-id instance
		Session s = openSession();
		s.beginTransaction();
		Customer cust = new Customer( "Acme, Inc." );
		Order order = new Order( new Order.Id( cust, 1 ) );
		cust.getOrders().add( order );
		s.save( cust );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		try {
			cust = ( Customer ) s.get( Customer.class, cust.getId() );
		}
		catch( OverflowCondition overflow ) {
			fail( "get()/load() caused overflow condition" );
		}
		s.delete( cust );
		s.getTransaction().commit();
		s.close();
	}
