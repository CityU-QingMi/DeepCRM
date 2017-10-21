	@Test
	@TestForIssue( jiraKey = "" )
	public void testSelectClauseCaseWithSum() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Human h1 = new Human();
		h1.setBodyWeight( 74.0f );
		h1.setDescription( "Me" );
		s.persist( h1 );

		Human h2 = new Human();
		h2.setBodyWeight( 125.0f );
		h2.setDescription( "big persion #1" );
		s.persist( h2 );

		Human h3 = new Human();
		h3.setBodyWeight( 110.0f );
		h3.setDescription( "big persion #2" );
		s.persist( h3 );

		s.flush();

		Number count = (Number) s.createQuery( "select sum(case when bodyWeight > 100 then 1 else 0 end) from Human" ).uniqueResult();
		assertEquals( 2, count.intValue() );
		count = (Number) s.createQuery( "select sum(case when bodyWeight > 100 then bodyWeight else 0 end) from Human" ).uniqueResult();
		assertEquals( h2.getBodyWeight() + h3.getBodyWeight(), count.floatValue(), 0.001 );

		t.rollback();
		s.close();
	}
