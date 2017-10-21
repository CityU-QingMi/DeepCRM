	@Test
	@SuppressWarnings("")
	public void testQueryNoPersonsSharedNotTransactional() {
		endTransaction();

		EntityManager em = SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
		Query q = em.createQuery("select p from Person as p");
		q.setFlushMode(FlushModeType.AUTO);
		List<Person> people = q.getResultList();
		assertEquals(0, people.size());
		try {
			assertNull(q.getSingleResult());
			fail("Should have thrown IllegalStateException");
		}
		catch (Exception ex) {
			// We would typically expect an IllegalStateException, but Hibernate throws a
			// PersistenceException. So we assert the contents of the exception message instead.
			assertTrue(ex.getMessage().contains("closed"));
		}
		q = em.createQuery("select p from Person as p");
		q.setFlushMode(FlushModeType.AUTO);
		try {
			assertNull(q.getSingleResult());
			fail("Should have thrown NoResultException");
		}
		catch (NoResultException ex) {
			// expected
		}
	}
