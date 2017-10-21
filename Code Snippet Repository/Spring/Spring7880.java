	@Test
	@SuppressWarnings({ "", "" })
	public void testQueryNoPersonsShared() {
		EntityManager em = SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
		Query q = em.createQuery("select p from Person as p");
		q.setFlushMode(FlushModeType.AUTO);
		List<Person> people = q.getResultList();
		try {
			assertNull(q.getSingleResult());
			fail("Should have thrown NoResultException");
		}
		catch (NoResultException ex) {
			// expected
		}
	}
