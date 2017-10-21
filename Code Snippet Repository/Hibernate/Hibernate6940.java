	@Test
	@TestForIssue( jiraKey = "")
	@SkipForDialect(value = SybaseDialect.class, comment = "")
	public void testNativeQueryWithNullParameterTyped(){
		Chaos c0 = new Chaos();
		c0.setId( 0L );
		c0.setName( "c0" );
		c0.setSize( 0L );
		Chaos c1 = new Chaos();
		c1.setId( 1L );
		c1.setName( "c1" );
		c1.setSize( 1L );
		Chaos c2 = new Chaos();
		c2.setId( 2L );
		c2.setName( "c2" );
		c2.setSize( null );

		Session s = openSession();
		s.beginTransaction();
		s.persist( c0 );
		s.persist( c1 );
		s.persist( c2 );

		s.flush();
		s.clear();

		List chaoses = s.createSQLQuery( "select * from CHAOS where chaos_size is null or chaos_size = :chaos_size" )
				.setParameter( "chaos_size", null, StandardBasicTypes.LONG )
				.list();
		assertEquals( 1, chaoses.size() );

		chaoses = s.createSQLQuery( "select * from CHAOS where chaos_size = :chaos_size" )
				.setParameter( "chaos_size", null, StandardBasicTypes.LONG )
				.list();
		// should be no results because null != null
		assertEquals( 0, chaoses.size() );

		s.getTransaction().rollback();
		s.close();
	}
