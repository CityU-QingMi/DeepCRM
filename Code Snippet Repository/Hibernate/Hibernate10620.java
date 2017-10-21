	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist( testEntity );
		em.getTransaction().commit();
		em.close();
	}
