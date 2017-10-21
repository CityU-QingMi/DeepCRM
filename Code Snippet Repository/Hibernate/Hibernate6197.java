	@Test
	@TestForIssue(jiraKey = "")
	public void testProxyObject() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Book book = new Book();
			em.persist( book );
			em.flush();
			em.clear(); // Clear persistence context to receive proxy object below.
			Book proxy = em.getReference( Book.class, book.getId() );
			assertTrue( proxy instanceof HibernateProxy );
			assertEquals( book.getId(), em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier( proxy ) );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}

		em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Author author = new Author();
			Article article = new Article( author );
			em.persist( author );
			em.persist( article );
			em.flush();
			em.clear(); // Clear persistence context to receive proxy relation below.
			article = em.find( Article.class, article.getId() );
			assertTrue( article.getAuthor() instanceof HibernateProxy );
			assertEquals(
					author.getId(),
					em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier( article.getAuthor() )
			);

		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
