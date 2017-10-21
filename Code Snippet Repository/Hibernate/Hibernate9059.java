	@Test
	public void testComponentPropertyRef() {
		Session s = openSession();
		s.beginTransaction();
		Person p = new Person();
		p.setIdentity( new Identity() );
		Account a = new Account();
		a.setNumber("123-12345-1236");
		a.setOwner(p);
		p.getIdentity().setName("Gavin");
		p.getIdentity().setSsn("123-12-1234");
		s.persist(p);
		s.persist(a);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		a = (Account) s.createQuery("from Account a left join fetch a.owner").uniqueResult();
		assertTrue( Hibernate.isInitialized( a.getOwner() ) );
		assertNotNull( a.getOwner() );
		assertEquals( "Gavin", a.getOwner().getIdentity().getName() );
		s.clear();
		
		a = (Account) s.get(Account.class, "123-12345-1236");
		assertFalse( Hibernate.isInitialized( a.getOwner() ) );
		assertNotNull( a.getOwner() );
		assertEquals( "Gavin", a.getOwner().getIdentity().getName() );
		assertTrue( Hibernate.isInitialized( a.getOwner() ) );
		
		s.clear();

		sessionFactory().getCache().evictEntityRegion( Account.class );
		sessionFactory().getCache().evictEntityRegion( Person.class );
		
		a = (Account) s.get(Account.class, "123-12345-1236");
		assertTrue( Hibernate.isInitialized( a.getOwner() ) );
		assertNotNull( a.getOwner() );
		assertEquals( "Gavin", a.getOwner().getIdentity().getName() );
		assertTrue( Hibernate.isInitialized( a.getOwner() ) );
		
		s.delete( a );
		s.delete( a.getOwner() );
		s.getTransaction().commit();
		s.close();
	}
