	@Test
	public void testVersionUnchangedPrimitiveCharArray() throws Exception {
		VersionedBook book = createBook();
		Editor editor = new Editor();
		editor.setName( "O'Reilly" );
		book.setEditor( editor );
		book.setCode2( new char[] { 'r' } );
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( book );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		VersionedBook loadedBook = getBookClass().cast( s.get( getBookClass(), getId( book ) ) );
		assertEquals( loadedBook.getVersion(), Integer.valueOf( 0 ) );
		s.flush();
		assertEquals( loadedBook.getVersion(), Integer.valueOf( 0 ) );
		s.delete( loadedBook );
		tx.commit();
		s.close();

	}
