	@Test
	public void testIdNullSetByPrePersist() throws Exception {
		Plant plant = new Plant();
		plant.setName( "Origuna plantula gigantic" );
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( plant );
		em.flush();
		em.getTransaction().rollback();
		em.close();
	}
