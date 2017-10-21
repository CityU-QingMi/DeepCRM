	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		EnumTestEntity ete = new EnumTestEntity( EnumTestEntity.E1.X, EnumTestEntity.E2.A );
		em.persist( ete );
		id1 = ete.getId();
		em.getTransaction().commit();

		em.getTransaction().begin();
		ete = em.find( EnumTestEntity.class, id1 );
		ete.setEnum1( EnumTestEntity.E1.Y );
		ete.setEnum2( EnumTestEntity.E2.B );
		em.getTransaction().commit();
	}
