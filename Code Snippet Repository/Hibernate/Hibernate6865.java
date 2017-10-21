	@Test
	public void testSimpleOneToManySet() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Ticket t = new Ticket();
		t.setNumber( "33A" );
		Ticket t2 = new Ticket();
		t2.setNumber( "234ER" );
		Customer c = new Customer();
		s.persist( c );
		//s.persist(t);
		SortedSet<Ticket> tickets = new TreeSet<Ticket>( new TicketComparator() );
		tickets.add( t );
		tickets.add( t2 );
		c.setTickets( tickets );

		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		c = ( Customer ) s.load( Customer.class, c.getId() );
		assertNotNull( c );
		assertTrue( Hibernate.isInitialized( c.getTickets() ) );
		assertNotNull( c.getTickets() );
		tickets = c.getTickets();
		assertTrue( tickets.size() > 0 );
		assertEquals( t2.getNumber(), c.getTickets().first().getNumber() );
		tx.commit();
		s.close();
	}
