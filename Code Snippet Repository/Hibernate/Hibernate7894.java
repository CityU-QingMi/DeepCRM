	@Test
	@TestForIssue( jiraKey = "" )
	public void testSelectClauseCaseWithCountDistinct() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Human h1 = new Human();
		h1.setBodyWeight( 74.0f );
		h1.setDescription( "Me" );
		h1.setNickName( "Oney" );
		s.persist( h1 );

		Human h2 = new Human();
		h2.setBodyWeight( 125.0f );
		h2.setDescription( "big persion" );
		h2.setNickName( "big #1" );
		s.persist( h2 );

		Human h3 = new Human();
		h3.setBodyWeight( 110.0f );
		h3.setDescription( "big persion" );
		h3.setNickName( "big #2" );
		s.persist( h3 );

		s.flush();

		Number count = (Number) s.createQuery( "select count(distinct case when bodyWeight > 100 then description else null end) from Human" ).uniqueResult();
		assertEquals( 1, count.intValue() );
		count = (Number) s.createQuery( "select count(case when bodyWeight > 100 then description else null end) from Human" ).uniqueResult();
		assertEquals( 2, count.intValue() );
		count = (Number) s.createQuery( "select count(distinct case when bodyWeight > 100 then nickName else null end) from Human" ).uniqueResult();
		assertEquals( 2, count.intValue() );

		t.rollback();
		s.close();
	}
