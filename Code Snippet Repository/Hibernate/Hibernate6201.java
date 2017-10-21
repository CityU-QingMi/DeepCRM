	@Test
	public void testIsLoadedOnPrivateSuperclassProperty() {
		EntityManager em = entityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		try {
			Author a = new Author();
			Book book = new Book( a );
			em.persist( a );
			em.persist( book );
			em.flush();
			em.clear();
			book = em.find( Book.class, book.getId() );
			assertTrue( em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded( book ) );
			assertFalse( em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded( book, "author" ) );
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
