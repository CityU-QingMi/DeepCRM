	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			final Author author = new Author();
			author.setFirstName( "TestFirstName" );
			author.setLastName( "lastName" );
			author.addBook( createBook1() );
			author.addBook( createBook2() );
			entityManager.persist( author );
			authorId = author.getId();
			entityManager.getTransaction().commit();
		}
		catch ( Exception e ) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
		}
		finally {
			entityManager.close();
		}
		// Revision 2
		// Removes all books and re-adds original 2 plus one new book
		entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			final Author author = entityManager.find( Author.class, authorId );
			author.removeAllBooks();
			author.addBook( createBook1() );
			author.addBook( createBook2() );
			author.addBook( createBook3() );
			entityManager.merge( author );
			entityManager.getTransaction().commit();
		}
		catch ( Exception e ) {
			if( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
		}
		finally {
			entityManager.close();
		}
	}
