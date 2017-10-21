	@Test
	@TestForIssue( jiraKey = "" )
	public void testScalarSQLQuery() {
		sessionFactory().getCache().evictQueryRegions();
		sessionFactory().getStatistics().clear();

		Session s = openSession();
		s.beginTransaction();
		Item item = new Item();
		item.setName("fooName");
		item.setDescription("fooDescription");
		s.persist(item);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		
		// Note: StandardQueryCache#put handles single results and multiple results differently.  So, test both
		// 1 and 2+ scalars.
		
        String sqlQuery = "select name, description from Items";
        SQLQuery query = s.createSQLQuery(sqlQuery);
        query.setCacheable(true);
        query.addScalar("name");
        query.addScalar("description");
        Object[] result1 = (Object[]) query.uniqueResult();
        assertNotNull( result1 );
        assertEquals( result1.length, 2 );
        assertEquals( result1[0], "fooName" );
        assertEquals( result1[1], "fooDescription" );
		
        sqlQuery = "select name from Items";
        query = s.createSQLQuery(sqlQuery);
        query.setCacheable(true);
        query.addScalar("name");
        String result2 = (String) query.uniqueResult();
        assertNotNull( result2 );
        assertEquals( result2, "fooName" );
        
        s.getTransaction().commit();
        s.close();
	}
