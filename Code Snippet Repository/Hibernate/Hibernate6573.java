	@Test
	public void testExtraLazy() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Person p = new Person( "Gavin", "King", "JBoss Inc" );
		Stay stay = new Stay( p, new Date(), new Date(), "A380", "Blah", "Blah" );
		p.getOrderedStay().add( stay );
		s.persist( p );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		p = (Person) s.createQuery( "from Person p where p.firstName = :name" )
				.setParameter( "name", "Gavin" ).uniqueResult();
		assertFalse( Hibernate.isInitialized( p.getOrderedStay() ) );
		assertEquals( 1, p.getOrderedStay().size() );
		assertFalse( Hibernate.isInitialized( p.getOrderedStay() ) );
		assertEquals( "A380", p.getOrderedStay().get(0).getVessel() );
		assertFalse( Hibernate.isInitialized( p.getOrderedStay() ) );
		s.delete( p );
		tx.commit();
		s.close();
	}
