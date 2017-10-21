	@Test
	public void testPrePersistOnCascade() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Television tv = new Television();
		RemoteControl rc = new RemoteControl();
		em.persist( tv );
		em.flush();
		tv.setControl( rc );
		tv.init();
		em.flush();
		assertNotNull( rc.getCreationDate() );
		em.getTransaction().rollback();
		em.close();
	}
