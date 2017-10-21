	@Test
	public void breakReferences() {
		doInHibernate( this::sessionFactory, sess -> {
			List<Book> books = sess.createQuery( "FROM Book").list();
			assertEquals(2, books.size());

			for (Book book : books) {
				assertNotNull(book.getLibrary());
				log.info("Found SKU " + book.getInventoryCode() + " with library " + book.getLibrary().getEntid());
			}

			for (Book book : books) {
				book.getLibrary().removeBook( book );
			}
		} );
		doInHibernate( this::sessionFactory, sess -> {
			List<Book> books = sess.createQuery( "FROM Book").list();
			assertEquals(2, books.size());

			for (Book book : books) {
				if (book.getLibrary() == null ) {
					log.info("Found SKU " + book.getInventoryCode() + " with no library");
				}
			}

			List<Library> libraries = sess.createQuery( "FROM Library").list();
			assertEquals(1, libraries.size());
			Library library = libraries.get( 0);
			assertNotNull(library);

			assertEquals(0, library.getBooksOnInventory().size());
			log.info("Found Library " + library.getEntid() + " with no books");
		} );
	}
