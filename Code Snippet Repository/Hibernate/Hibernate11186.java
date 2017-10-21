	@Test
	public void testDataNotPersisted() {
		// Checking if the entity became persisted
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Long count = (Long) em.createQuery( "select count(s) from StrTestEntity s where s.str = 'x'" )
				.getSingleResult();
		assert count == 0l;
		em.getTransaction().commit();
	}
