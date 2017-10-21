    @Test
	@RequiresDialect(H2Dialect.class)
	public void testCaseClause() {
		doInHibernate( this::sessionFactory, session -> {
			CriteriaBuilder cb = session.getCriteriaBuilder();

			CriteriaQuery<Event> criteria = cb.createQuery(Event.class);

			Root<Event> event = criteria.from(Event.class);
			Path<EventType> type = event.get("type");

			Expression<String> caseWhen = cb.<EventType, String>selectCase(type)
					.when(EventType.TYPE1, "Admin Event")
					.when(EventType.TYPE2, "User Event")
					.when(EventType.TYPE3, "Reporter Event")
					.otherwise("");

			criteria.select(event);
			criteria.where(cb.equal(caseWhen, "Admin Event")); // OK when use cb.like() method and others
			List<Event> resultList = session.createQuery(criteria).getResultList();

			Assert.assertNotNull(resultList);
		} );
    }
