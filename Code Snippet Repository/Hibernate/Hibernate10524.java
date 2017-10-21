	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		em.getTransaction().begin();
		EnumTypeEntity entity = new EnumTypeEntity( EnumTypeEntity.E1.X, EnumTypeEntity.E2.A );
		em.persist( entity );
		em.getTransaction().commit();

		em.close();
	}
