	@Test
	public void testRollbackClearPC() throws Exception {
		Book book = new Book();
		book.name = "Stolen keys";
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( book );
		em.getTransaction().commit();
		em.getTransaction().begin();
		book.name = "Recovered keys";
		em.merge( book );
		em.getTransaction().rollback();
		em.getTransaction().begin();
		assertEquals( "Stolen keys", em.find( Book.class, book.id ).name );
		em.getTransaction().commit();
		em.close();
	}
