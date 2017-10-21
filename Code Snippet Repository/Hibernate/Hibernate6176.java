	@Test
	public void testTransactionCommitDoesNotFlush() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Book book = new Book();
		book.name = "Java for Dummies";
		em.persist( book );
		em.getTransaction().commit();
		em.close();
		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		List result = em.createQuery( "select book from Book book where book.name = :title" ).
				setParameter( "title", book.name ).getResultList();
		assertEquals( "EntityManager.commit() should trigger a flush()", 1, result.size() );
		em.getTransaction().commit();
		em.close();
	}
