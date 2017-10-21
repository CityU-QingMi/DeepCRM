	@Test
	public void lookupEntities() {
		doInHibernate( this::sessionFactory, sess -> {
			List<Library> libraries = sess.createQuery( "FROM Library").list();
			assertEquals(1, libraries.size());
			Library library = libraries.get( 0);
			assertNotNull(library);

			assertEquals(2, library.getBooksOnInventory().size());

			Book book = library.getBooksOnInventory().get( SKU001);
			assertNotNull(book);
			Library Library = library;
			Library.getBooksOnIsbn().get( WAR_AND_PEACE );
			assertEquals(WAR_AND_PEACE, book.getIsbn());

			book = library.getBooksOnInventory().get(SKU002);
			assertNotNull(book);
			assertEquals(ANNA_KARENINA, book.getIsbn());
		} );
	}
