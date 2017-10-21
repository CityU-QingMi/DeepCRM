	@Test
	@TestForIssue( jiraKey = "")
	public void testSubCriteriaRestrictionsOnCollectionsNestedInManyToOne() {
		// the result of all Criteria in this test will be order1, because that's the only Order with:
		// 1) orderContacts containing an OrderContact with contact == "Contact1"
		// and 2) orderAddress.notifiedAddresses containing an Address with addressText == "over the rainbow"

		Session s = openSession();
		s.getTransaction().begin();
		Criteria rootCriteria = s.createCriteria(Order.class, "order");
		rootCriteria.createCriteria("order.orderContacts", "contact", JoinType.LEFT_OUTER_JOIN)
				.add( Restrictions.eq( "contact.contact", "Contact1" ) );
		rootCriteria.createCriteria( "order.orderAddress", "orderAddress", JoinType.LEFT_OUTER_JOIN )
				.createCriteria( "orderAddress.notifiedAddresses", "notifiedAddress", JoinType.LEFT_OUTER_JOIN )
				.add( Restrictions.eq( "notifiedAddress.addressText", "over the rainbow" ) );

		// Since restrictions are on subcriteria, the collections should n on orderLines is on root criteria, that collection should be filtered.
		// Since restriction on orderContacts is on a subcriteria, that collection should be
		// non-filtered (contain all its elements)
		Order result = (Order) rootCriteria.uniqueResult();
		assertEquals( order1.getOrderId(), result.getOrderId() );
		assertEquals( 2, result.getContacts().size() );
		assertEquals( 2, result.getOrderAddress().getNotifiedAddresses().size() );
		s.getTransaction().commit();
		s.close();

		// The following should have the same result as the previous, since it has the same
		// restrictions applied in reverse order.

		s = openSession();
		s.getTransaction().begin();
		rootCriteria = s.createCriteria(Order.class, "order");
		rootCriteria.createCriteria( "order.orderAddress", "orderAddress", JoinType.LEFT_OUTER_JOIN )
				.createCriteria( "orderAddress.notifiedAddresses", "notifiedAddress", JoinType.LEFT_OUTER_JOIN )
				.add( Restrictions.eq( "notifiedAddress.addressText", "over the rainbow" ) );
		rootCriteria.createCriteria("order.orderContacts", "contact", JoinType.LEFT_OUTER_JOIN)
				.add( Restrictions.eq( "contact.contact", "Contact1" ) );
		// Since restrictions are on subcriteria, the collections should n on orderLines is on root criteria, that collection should be filtered.
		// Since restriction on orderContacts is on a subcriteria, that collection should be
		// non-filtered (contain all its elements)
		result = (Order) rootCriteria.uniqueResult();
		assertEquals( order1.getOrderId(), result.getOrderId() );
		assertEquals( 2, result.getContacts().size() );
		assertEquals( 2, result.getOrderAddress().getNotifiedAddresses().size() );
		s.getTransaction().commit();
		s.close();
	}
