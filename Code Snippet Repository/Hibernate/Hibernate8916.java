	@Test
	public void lookupEntities_entrySet() {
		doInHibernate( this::sessionFactory, sess -> {
			List<Library> libraries = sess.createQuery( "FROM Library").list();
			assertEquals(1, libraries.size());
			Library library = libraries.get( 0);
			assertNotNull(library);

			assertEquals(2, library.getBooksOnInventory().size());

			for (Entry<String,Book> entry : library.getBooksOnInventory().entrySet()) {
				log.info("Found SKU " + entry.getKey() + " with ISBN " + entry.getValue().getIsbn());
			}
		} );
	}
