	@Test
	public void testParent() throws Exception {
		Session s;
		s = openSession();
		s.getTransaction().begin();
		Book book = new Book();
		book.setIsbn( "1234" );
		book.setName( "HiA Second Edition" );
		Summary summary = new Summary();
		summary.setText( "This is a HiA SE summary" );
		summary.setSize( summary.getText().length() );
		book.setSummary( summary );
		s.persist( book );
		s.getTransaction().commit();

		s.clear();

		Transaction tx = s.beginTransaction();
		Book loadedBook = (Book) s.get( Book.class, book.getIsbn() );
		assertNotNull( loadedBook.getSummary() );
		assertEquals( loadedBook, loadedBook.getSummary().getSummarizedBook() );
		s.delete( loadedBook );
		tx.commit();
		s.close();
	}
