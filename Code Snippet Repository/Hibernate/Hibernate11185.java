	@Test(expected = RuntimeException.class)
	public void testTransactionRollback() throws InterruptedException {
		// Trying to persist an entity - however the listener should throw an exception, so the entity
		// shouldn't be persisted
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		StrTestEntity te = new StrTestEntity( "x" );
		em.persist( te );
		em.getTransaction().commit();
	}
