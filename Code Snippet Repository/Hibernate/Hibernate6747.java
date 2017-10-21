	@Test
	public void testSerializableToBlob() throws Exception {
		B book = createBook();
		Editor editor = new Editor();
		editor.setName( "O'Reilly" );
		book.setEditor( editor );
		book.setCode2( new char[] { 'r' } );

		doInHibernate( this::sessionFactory, session -> {
			session.persist( book );
		} );

		doInHibernate( this::sessionFactory, session -> {
			B loadedBook = getBookClass().cast( session.get( getBookClass(), getId( book ) ) );
			assertNotNull( loadedBook.getEditor() );
			assertEquals( book.getEditor().getName(), loadedBook.getEditor().getName() );
			loadedBook.setEditor( null );
		} );

		doInHibernate( this::sessionFactory, session -> {
			B loadedBook = getBookClass().cast( session.get( getBookClass(), getId( book ) ) );
			assertNull( loadedBook.getEditor() );
		} );
	}
