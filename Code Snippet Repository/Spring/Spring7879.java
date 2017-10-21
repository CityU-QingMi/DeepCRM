	@Test
	@SuppressWarnings("")
	public void testQueryNoPersonsNotTransactional() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createQuery("select p from Person as p");
		List<Person> people = q.getResultList();
		assertEquals(0, people.size());
		try {
			assertNull(q.getSingleResult());
			fail("Should have thrown NoResultException");
		}
		catch (NoResultException ex) {
			// expected
		}
	}
