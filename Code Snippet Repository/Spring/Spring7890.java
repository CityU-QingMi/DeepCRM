	@Test
	@SuppressWarnings("")
	public void testEntityManagerProxyIsProxy() {
		EntityManager em = createContainerManagedEntityManager();
		assertTrue(Proxy.isProxyClass(em.getClass()));
		Query q = em.createQuery("select p from Person as p");
		List<Person> people = q.getResultList();
		assertTrue(people.isEmpty());

		assertTrue("Should be open to start with", em.isOpen());
		try {
			em.close();
			fail("Close should not work on container managed EM");
		}
		catch (IllegalStateException ex) {
			// OK
		}
		assertTrue(em.isOpen());
	}
