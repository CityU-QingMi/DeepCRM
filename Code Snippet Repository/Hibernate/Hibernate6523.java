	@Test
	public void testInheritFiltersFromMappedSuperclass() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.createQuery( "delete Drill" ).executeUpdate();
		Drill d1 = new PowerDrill();
		d1.setName("HomeDrill1");
		d1.setCategory("HomeImprovment");
		s.persist( d1 );
		Drill d2 = new PowerDrill();
		d2.setName("HomeDrill2");
		d2.setCategory("HomeImprovement");
		s.persist(d2);
		Drill d3 = new PowerDrill();
		d3.setName("HighPowerDrill");
		d3.setCategory("Industrial");
		s.persist( d3 );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		 
		//We test every filter with 2 queries, the first on the base class of the 
		//inheritance hierarchy (Drill), and the second on a subclass (PowerDrill)
		s.enableFilter( "byName" ).setParameter( "name", "HomeDrill1");
		long count = ( (Long) s.createQuery( "select count(*) from Drill" ).iterate().next() ).intValue();
		assertEquals( 1, count );
		count = ( (Long) s.createQuery( "select count(*) from PowerDrill" ).iterate().next() ).intValue();
		assertEquals( 1, count );
		s.disableFilter( "byName" );
		
		s.enableFilter( "byCategory" ).setParameter( "category", "Industrial" );
		count = ( (Long) s.createQuery( "select count(*) from Drill" ).iterate().next() ).longValue();
		assertEquals( 1, count );
		count = ( (Long) s.createQuery( "select count(*) from PowerDrill" ).iterate().next() ).longValue();
		assertEquals( 1, count );
		s.disableFilter( "byCategory" );
		
		tx.rollback();
		s.close();
	}
