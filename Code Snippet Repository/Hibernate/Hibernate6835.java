	@TestForIssue(jiraKey = "")
	@Test
	public void testCriteriaWithFetchModeJoinOnetoOne() {
		Session s = openSession();
		Transaction newTx = s.getTransaction();

		newTx.begin();
		A a1 = new A();
		a1.setName( "name1" );
		D d1 = new D();
		a1.setSingleD( d1 );
		d1.setSingleA( a1 );
		s.persist( d1 );
		s.persist( a1 );
		newTx.commit();

		newTx = s.beginTransaction();
		getCriteria( s ).setFetchMode( "singleD", FetchMode.JOIN ).uniqueResult(); // put query-result into cache
		A a2 = new A();
		a2.setName( "xxxxxx" );
		s.persist( a2 );
		newTx.commit();	  // Invalidates space A in UpdateTimeStamps region

		//Create new session to avoid the session cache which can't be tracked
		s.close();
		s = openSession();

		newTx = s.beginTransaction();

		Assert.assertTrue( s.getSessionFactory().getStatistics().isStatisticsEnabled() );
		s.getSessionFactory().getStatistics().clear();

		// should produce a hit in StandardQuery cache region
		getCriteria( s ).setFetchMode( "singleD", FetchMode.JOIN ).uniqueResult();

		Assert.assertEquals(
				"query is not considered as isImmutableNaturalKeyLookup, despite fullfilling all conditions",
				1, s.getSessionFactory().getStatistics().getNaturalIdCacheHitCount()
		);
		s.createQuery( "delete from A" ).executeUpdate();
		s.createQuery( "delete from D" ).executeUpdate();

		newTx.commit();
		// Shutting down the application
		s.close();
	}
