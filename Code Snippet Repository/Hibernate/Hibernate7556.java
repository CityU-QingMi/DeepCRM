	@Test
	@TestForIssue( jiraKey = "")
	public void testMultipleSubCriteriaRestrictionsOnCollections() {
		Session s = openSession();
		s.getTransaction().begin();

		Criteria rootCriteria = s.createCriteria(Order.class, "order");
		rootCriteria.createCriteria( "order.orderLines", "line", JoinType.LEFT_OUTER_JOIN )
				.add(Restrictions.eq("line.articleId", "3000"));
		rootCriteria.createCriteria( "order.orderContacts", "contact", JoinType.LEFT_OUTER_JOIN )
				.add(Restrictions.eq("contact.contact", "Contact1"));
		// result should be order1, because that's the only Order with:
		// 1) orderLines containing an OrderLine with articleId == "3000"
		// and 2) orderContacts containing an OrderContact with contact == "Contact1"
		// Since both restrictions are in subcriteria, both collections should be non-filtered
		// (i.e. includes all elements in both collections)
		Order result = (Order) rootCriteria.uniqueResult();
		assertEquals( order1.getOrderId(), result.getOrderId() );
		assertEquals( 2, result.getContacts().size() );
		assertEquals( 2, result.getLines().size() );
		s.getTransaction().commit();
		s.close();
	}
