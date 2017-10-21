	@Test
	public void testHibernateFetchingLazy() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Person p = new Person( "Gavin", "King", "JBoss Inc" );
		Stay stay = new Stay( null, new Date(), new Date(), "A380", "Blah", "Blah" );
		Stay stay2 = new Stay( null, new Date(), new Date(), "A320", "Blah", "Blah" );
		Stay stay3 = new Stay( null, new Date(), new Date(), "A340", "Blah", "Blah" );
		stay.setOldPerson( p );
		stay2.setVeryOldPerson( p );
		stay3.setVeryOldPerson( p );
		p.addOldStay( stay );
		p.addVeryOldStay( stay2 );
		p.addVeryOldStay( stay3 );
		s.persist( p );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		p = (Person) s.createQuery( "from Person p where p.firstName = :name" )
				.setParameter( "name", "Gavin" ).uniqueResult();
		assertFalse( Hibernate.isInitialized( p.getOldStays() ) );
		assertEquals( 1, p.getOldStays().size() );
		assertFalse( "lazy extra is failing", Hibernate.isInitialized( p.getOldStays() ) );
		s.clear();
		stay = (Stay) s.get( Stay.class, stay.getId() );
		assertTrue( ! Hibernate.isInitialized( stay.getOldPerson() ) );
		s.clear();
		stay3 = (Stay) s.get( Stay.class, stay3.getId() );
		assertTrue( "FetchMode.JOIN should overrides lazy options", Hibernate.isInitialized( stay3.getVeryOldPerson() ) );
		s.delete( stay3.getVeryOldPerson() );
		tx.commit();
		s.close();
	}
