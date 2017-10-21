	@Test
	@TestForIssue( jiraKey = "" )
	public void testNotEqualOperator() {
		final Session s = openSession();
		s.getTransaction().begin();

		final Transaction t1 = new Transaction();
		t1.setDescription( "foo" );
		t1.setValue( new MonetoryAmount( new BigDecimal( 178 ), Currency.getInstance( "EUR" ) ) );
		t1.setTimestamp( new CompositeDateTime( 2014, 8, 23, 14, 23, 0 ) );
		s.persist( t1 );

		final Transaction t2 = new Transaction();
		t2.setDescription( "bar" );
		t2.setValue( new MonetoryAmount( new BigDecimal( 1000000 ), Currency.getInstance( "USD" ) ) );
		t1.setTimestamp( new CompositeDateTime( 2014, 8, 22, 14, 23, 0 ) );
		s.persist( t2 );

		final Transaction t3 = new Transaction();
		t3.setDescription( "bar" );
		t3.setValue( new MonetoryAmount( new BigDecimal( 1000000 ), Currency.getInstance( "EUR" ) ) );
		t3.setTimestamp( new CompositeDateTime( 2014, 8, 22, 14, 23, 01 ) );
		s.persist( t3 );

		final Query q1 = s.createQuery( "from Transaction where value <> :amount" );
		q1.setParameter( "amount", new MonetoryAmount( new BigDecimal( 178 ), Currency.getInstance( "EUR" ) ) );
		assertEquals( 2, q1.list().size() );

		final Query q2 = s.createQuery( "from Transaction where value <> :amount and description = :str" );
		q2.setParameter( "amount", new MonetoryAmount( new BigDecimal( 1000000 ), Currency.getInstance( "USD" ) ) );
		q2.setParameter( "str", "bar" );
		assertEquals( 1, q2.list().size() );

		final Query q3 = s.createQuery( "from Transaction where timestamp <> :timestamp" );
		q3.setParameter( "timestamp", new CompositeDateTime( 2014, 8, 23, 14, 23, 0 ) );
		assertEquals( 2, q3.list().size() );

		s.delete( t3 );
		s.delete( t2 );
		s.delete( t1 );
		s.getTransaction().commit();
		s.close();
	}
