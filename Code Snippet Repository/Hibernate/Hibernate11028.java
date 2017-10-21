	@Test
	@Priority(10)
	public void initData() {
		ed1_id = new MulId( 1, 2 );
		ed2_id = new MulId( 3, 4 );

		ing1_id = new MulId( 5, 6 );

		BiMulIdRefEdEntity ed1 = new BiMulIdRefEdEntity( ed1_id.getId1(), ed1_id.getId2(), "data_ed_1" );
		BiMulIdRefEdEntity ed2 = new BiMulIdRefEdEntity( ed2_id.getId1(), ed2_id.getId2(), "data_ed_2" );

		BiMulIdRefIngEntity ing1 = new BiMulIdRefIngEntity( ing1_id.getId1(), ing1_id.getId2(), "data_ing_1" );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		ing1.setReference( ed1 );

		em.persist( ed1 );
		em.persist( ed2 );

		em.persist( ing1 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ing1 = em.find( BiMulIdRefIngEntity.class, ing1_id );
		ed2 = em.find( BiMulIdRefEdEntity.class, ed2_id );

		ing1.setReference( ed2 );

		em.getTransaction().commit();
	}
