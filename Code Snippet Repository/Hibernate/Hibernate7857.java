	@Test
	public void testClearBatchFetchQueueAfterFlush() {
		Session s = openSession();
		s.beginTransaction();

		Author author1 = new Author( "David Lodge" );
		author1.getBooks().add( new Book( "A Man of Parts", author1 ) );
		author1.getBooks().add( new Book( "Thinks...", author1 ) );
		author1.getBooks().add( new Book( "Therapy", author1 ) );
		s.save( author1 );

		Iterator<Book> bookIterator = author1.getBooks().iterator();

		BookStore bookStore1 = new BookStore( "Passages" );
		bookStore1.getBooks().add( bookIterator.next() );
		s.save( bookStore1 );

		BookStore bookStore2 = new BookStore( "Librairie du Tramway" );
		bookStore2.getBooks().add( bookIterator.next() );
		s.save( bookStore2 );

		BookStore bookStore3 = new BookStore( "Le Bal des Ardents" );
		bookStore3.getBooks().add( bookIterator.next() );
		s.save( bookStore3 );

		s.flush();
		s.getTransaction().commit();
		s.clear();

		bookStore1 = s.load( BookStore.class, bookStore1.getId() );
		bookStore2 = s.load( BookStore.class, bookStore2.getId() );
		bookStore3 = s.load( BookStore.class, bookStore3.getId() );

		s.beginTransaction();
		s.delete( bookStore2 );
		s.getTransaction().commit();

		bookStore1.getBooks().size();
		bookStore3.getBooks().size();
	}
