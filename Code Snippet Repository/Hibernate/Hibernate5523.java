	@Test
	public void testIsOpen() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		assertTrue( em.isOpen() );
		em.getTransaction().begin();
		assertTrue( em.isOpen() );
		em.getTransaction().rollback();
		em.close();
		assertFalse( em.isOpen() );
	}
