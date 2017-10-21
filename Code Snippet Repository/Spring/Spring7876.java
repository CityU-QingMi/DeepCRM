	@Test
	@SuppressWarnings("")
	public void testMultipleResults() {
		// Add with JDBC
		String firstName = "Tony";
		insertPerson(firstName);

		assertTrue(Proxy.isProxyClass(sharedEntityManager.getClass()));
		Query q = sharedEntityManager.createQuery("select p from Person as p");
		List<Person> people = q.getResultList();

		assertEquals(1, people.size());
		assertEquals(firstName, people.get(0).getFirstName());
	}
