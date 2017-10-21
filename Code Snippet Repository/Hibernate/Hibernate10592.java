	@Test
	public void testAuthorLastRevision() {
		// tests that Author has 3 books, Book1, Book2, and Book3.
		// where Book1 and Book2 were removed and re-added with the addition of Book3.
		EntityManager entityManager = getEntityManager();
		try {
			final AuditReader reader = getAuditReader();
			final List<Number> revisions = reader.getRevisions( Author.class, authorId );
			final Number lastRevision = revisions.get( revisions.size() - 1 );

			final Author author = (Author) reader.createQuery()
					.forEntitiesAtRevision( Author.class, lastRevision )
					.getSingleResult();

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
