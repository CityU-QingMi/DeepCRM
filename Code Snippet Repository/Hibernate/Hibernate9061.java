	@Test
	public void testOneToOnePropertyRef() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Customer c = new Customer();
		c.setName( "Emmanuel" );
		c.setCustomerId( "C123-456" );
		c.setPersonId( "P123-456" );
		Account a = new Account();
		a.setCustomer( c );
		a.setPerson( c );
		a.setType( 'X' );
		s.persist( c );
		s.persist( a );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		a = ( Account ) s.createQuery( "from Account acc join fetch acc.customer join fetch acc.person" )
				.uniqueResult();
		assertNotNull( a.getCustomer() );
		assertTrue( Hibernate.isInitialized( a.getCustomer() ) );
		assertNotNull( a.getPerson() );
		assertTrue( Hibernate.isInitialized( a.getPerson() ) );
		c = ( Customer ) s.createQuery( "from Customer" ).uniqueResult();
		assertSame( c, a.getCustomer() );
		assertSame( c, a.getPerson() );
		s.delete( a );
		s.delete( a.getCustomer() );
		s.delete( a.getPerson() );
		t.commit();
		s.close();
	}
