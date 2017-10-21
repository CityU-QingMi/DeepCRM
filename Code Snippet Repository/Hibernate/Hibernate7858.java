	@Test
	public void testInitializationDuringFlush() {
		assertFalse( InitializingPreUpdateEventListener.INSTANCE.executed );
		assertFalse( InitializingPreUpdateEventListener.INSTANCE.foundAny );

		Session s = openSession();
		s.beginTransaction();
		Publisher publisher = new Publisher( "acme" );
		Author author = new Author( "john" );
		author.setPublisher( publisher );
		publisher.getAuthors().add( author );
		author.getBooks().add( new Book( "Reflections on a Wimpy Kid", author ) );
		s.save( author );
		s.getTransaction().commit();
		s.clear();

		s = openSession();
		s.beginTransaction();
		publisher = (Publisher) s.get( Publisher.class, publisher.getId() );
		publisher.setName( "random nally" );
		s.flush();
		s.getTransaction().commit();
		s.clear();

		s = openSession();
		s.beginTransaction();
		s.delete( author );
		s.getTransaction().commit();
		s.clear();

		assertTrue( InitializingPreUpdateEventListener.INSTANCE.executed );
		assertTrue( InitializingPreUpdateEventListener.INSTANCE.foundAny );
	}
