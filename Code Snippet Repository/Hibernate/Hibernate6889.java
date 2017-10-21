	@Test
	public void testDefaultOneToOne() throws Exception {
		//test a default one to one and a mappedBy in the other side
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Customer c = new Customer();
		c.setName( "Hibernatus" );
		Passport p = new Passport();
		p.setNumber( "123456789" );
		s.persist( c ); //we need the id to assigned it to passport
		c.setPassport( p );
		p.setOwner( c );
		p.setId( c.getId() );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		c = ( Customer ) s.get( Customer.class, c.getId() );
		assertNotNull( c );
		p = c.getPassport();
		assertNotNull( p );
		assertEquals( "123456789", p.getNumber() );
		assertNotNull( p.getOwner() );
		assertEquals( "Hibernatus", p.getOwner().getName() );
		tx.commit(); // commit or rollback is the same, we don't care for read queries
		s.close();
	}
