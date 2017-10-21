	@Test
	@SuppressWarnings("")
	public void testEntityManagerProxyIsProxy() {
		EntityManager em = entityManagerFactory.createEntityManager();
		assertTrue(Proxy.isProxyClass(em.getClass()));
		Query q = em.createQuery("select p from Person as p");
		List<Person> people = q.getResultList();
		assertNotNull(people);

		assertTrue("Should be open to start with", em.isOpen());
		em.close();
		assertFalse("Close should work on application managed EM", em.isOpen());
	}
