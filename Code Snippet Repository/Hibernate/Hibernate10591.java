	public void testAuthorState() {
		EntityManager entityManager = getEntityManager();
		try {
			final Author author = entityManager.find( Author.class, authorId );
			assertNotNull( author );
			assertEquals( 3, author.getBooks().size() );
		}
		catch ( Exception e ) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
		}
		finally {
			entityManager.close();
		}
	}
