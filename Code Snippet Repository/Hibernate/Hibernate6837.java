	@TestForIssue(jiraKey = "")
	@Test
	public void testSubCriteriaOneToOneJoin() {
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
		getCriteria( s ).createCriteria( "singleD", "d", JoinType.LEFT_OUTER_JOIN )
				.uniqueResult(); // put query-result into cache
		A a2 = new A();
		a2.setName( "xxxxxx" );
		s.persist( a2 );
		newTx.commit();	  // Invalidates space A in UpdateTimeStamps region

		newTx = s.beginTransaction();

		// please enable
		// log4j.logger.org.hibernate.cache.StandardQueryCache=DEBUG
		// log4j.logger.org.hibernate.cache.UpdateTimestampsCache=DEBUG
		// to see that isUpToDate is called where not appropriated

		Assert.assertTrue( s.getSessionFactory().getStatistics().isStatisticsEnabled() );
		s.getSessionFactory().getStatistics().clear();

		// should not produce a hit in StandardQuery cache region because createCriteria() creates a subcriteria
		getCriteria( s ).createCriteria( "singleD", "d", JoinType.LEFT_OUTER_JOIN ).uniqueResult();

		Assert.assertEquals( 0, s.getSessionFactory().getStatistics().getQueryCacheHitCount() );
		s.createQuery( "delete from A" ).executeUpdate();
		s.createQuery( "delete from D" ).executeUpdate();

		newTx.commit();
		// Shutting down the application
		s.close();
	}
