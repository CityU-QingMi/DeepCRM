	@Test
	public void testMultipleBagFetchHql() throws Exception {

		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		Post post = new Post();
		post.setId( 1L );
		post.setTitle( String.format( "Post nr. %d", 1 ) );
		PostComment comment = new PostComment();
		comment.setId(1L);
		comment.setReview( "Excellent!" );
		session.persist(post);
		session.persist( comment );
		post.comments.add( comment );

		transaction.commit();
		session.close();


		session = openSession();
		session.beginTransaction();

		try {
			post = (Post) session.createQuery(
					"select p " +
							"from Post p " +
							"join fetch p.tags " +
							"join fetch p.comments " +
							"where p.id = :id"
			)
					.setParameter( "id", 1L )
					.uniqueResult();
			fail("Should throw org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags");
		}
		catch ( IllegalArgumentException expected ) {
			session.getTransaction().rollback();
			// MultipleBagFetchException was converted to IllegalArgumentException
			assertTrue( MultipleBagFetchException.class.isInstance( expected.getCause() ) );
		}
		finally {
			session.close();
		}
	}
