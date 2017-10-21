    @Test
    public void testEqualClause() {
		doInHibernate( this::sessionFactory, session -> {
			CriteriaBuilder cb = session.getCriteriaBuilder();

			CriteriaQuery<Event> criteria = cb.createQuery(Event.class);

			Root<Event> event = criteria.from(Event.class);
			Path<EventType> type = event.get("type");

			Expression<String> caseWhen = cb.<String>selectCase()
					.when(cb.equal(type, EventType.TYPE1), "Type1")
					.otherwise("");

			criteria.select(event);
			criteria.where(cb.equal(caseWhen, "Admin Event")); // OK when use cb.like() method and others
			List<Event> resultList = session.createQuery(criteria).getResultList();


			Assert.assertNotNull(resultList);
		} );
    }
