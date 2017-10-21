	@Test
	public void testSubselect() {
		Session s = openSession();

		s.getTransaction().begin();
		try {
			Author b = new Author();
			b.setName( "Camilleri" );
			b.setId( 1 );

			s.save( b );

			Book book = new Book();
			book.setId( 2 );
			book.setAuthorId( 1 );
			book.setTitle( "Il sognaglio" );
			s.save( book );

			Book book2 = new Book();
			book2.setId( 3 );
			book2.setAuthorId( 1 );
			book2.setTitle( "Il casellante" );

			s.save( book2 );

			s.getTransaction().commit();
		}
		catch (Exception e) {
			if ( s.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
				s.getTransaction().rollback();
			}
			fail( e.getMessage() );
		}
		finally {
			s.close();
		}

		s = openSession();
		try {
			Author author = s.get( Author.class, 1 );
			assertThat( author.getBooks().size(), is( 2 ) );
		}
		finally {
			s.close();
		}
	}
