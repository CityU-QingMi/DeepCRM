	@Test
	@SuppressWarnings({ "", "" })
	public void testEntityManagerProxyIsProxy() {
		assertTrue(Proxy.isProxyClass(sharedEntityManager.getClass()));
		Query q = sharedEntityManager.createQuery("select p from Person as p");
		List<Person> people = q.getResultList();

		assertTrue("Should be open to start with", sharedEntityManager.isOpen());
		sharedEntityManager.close();
		assertTrue("Close should have been silently ignored", sharedEntityManager.isOpen());
	}
