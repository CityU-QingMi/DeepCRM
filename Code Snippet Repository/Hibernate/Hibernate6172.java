	@Test
	public void testSetRollbackOnlyAndFlush() throws Exception {
		Book book = new Book();
		book.name = "The jungle book";
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.getTransaction().setRollbackOnly();
		em.persist( book );
		em.flush();
		em.getTransaction().rollback();
		em.getTransaction().begin();
		Query query = em.createQuery( "SELECT b FROM Book b WHERE b.name = :name" );
		query.setParameter( "name", book.name );
		assertEquals( 0, query.getResultList().size() );
		em.getTransaction().commit();
		em.close();
	}
