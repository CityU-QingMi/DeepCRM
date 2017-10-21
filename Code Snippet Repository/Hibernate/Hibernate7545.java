	@Test
	@TestForIssue( jiraKey = "")
	public void testMultipleRootCriteriaRestrictionsOnCollections() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria(Order.class, "order");
		rootCriteria.createAlias( "order.orderLines", "line", JoinType.LEFT_OUTER_JOIN )
				.add(Restrictions.eq("line.articleId", "3000"));
		rootCriteria.createAlias("order.orderContacts", "contact", JoinType.LEFT_OUTER_JOIN)
				.add( Restrictions.eq( "contact.contact", "Contact1" ) );
		// result should be order1, because that's the only Order with:
		// 1) orderLines containing an OrderLine with articleId == "3000"
		// and 2) orderContacts containing an OrderContact with contact == "Contact1"
		// Since both restrictions are in root criteria, both collections should be filtered
		// to contain only the elements that satisfy the restrictions.
		Order result = (Order) rootCriteria.uniqueResult();
		assertEquals( order1.getOrderId(), result.getOrderId() );
		assertEquals( 1, result.getLines().size() );
		assertEquals( "3000", result.getLines().iterator().next().getArticleId() );
		assertEquals( 1, result.getContacts().size() );
		assertEquals( "Contact1", result.getContacts().iterator().next().getContact() );
		s.getTransaction().commit();
		s.close();
	}
