	@Test
	public void testPersist() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( b );
		em.getTransaction().commit();
		em.close();

		check();
	}
