	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		EffectiveEntity1 entity = new EffectiveEntity1( 1L, "commonField", "specificField1" );
		em.persist( entity );
		em.getTransaction().commit();

		em.close();
	}
