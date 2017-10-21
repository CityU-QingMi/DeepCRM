	@Test
	public void testClob() throws Exception {

		B book = createBook();
		book.setShortDescription( "Hibernate Bible" );
		book.setFullText( "Hibernate in Action aims to..." );
		book.setCode( new Character[] { 'a', 'b', 'c' } );
		book.setCode2( new char[] { 'a', 'b', 'c' } );

		doInHibernate( this::sessionFactory, session -> {
			session.persist( book );
		} );

		doInHibernate( this::sessionFactory, session -> {
			B b2 = getBookClass().cast( session.get( getBookClass(), getId( book ) ) );
			assertNotNull( b2 );
			assertEquals( b2.getFullText(), book.getFullText() );
			assertEquals( b2.getCode()[1].charValue(), book.getCode()[1].charValue() );
			assertEquals( b2.getCode2()[2], book.getCode2()[2] );
		} );
	}
