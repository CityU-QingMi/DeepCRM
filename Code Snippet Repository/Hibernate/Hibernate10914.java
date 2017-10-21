	@Test
	@Priority(10)
	public void initData() {
		ed1_id = new MulId( 0, 1 );
		ed2_id = new MulId( 2, 3 );

		ing2_id = new MulId( 4, 5 );
		ing1_id = new MulId( 6, 7 );

		EntityManager em = getEntityManager();

		SetRefEdMulIdEntity ed1 = new SetRefEdMulIdEntity( ed1_id.getId1(), ed1_id.getId2(), "data_ed_1" );
		SetRefEdMulIdEntity ed2 = new SetRefEdMulIdEntity( ed2_id.getId1(), ed2_id.getId2(), "data_ed_2" );

		SetRefIngMulIdEntity ing1 = new SetRefIngMulIdEntity( ing1_id.getId1(), ing1_id.getId2(), "data_ing_1", ed1 );
		SetRefIngMulIdEntity ing2 = new SetRefIngMulIdEntity( ing2_id.getId1(), ing2_id.getId2(), "data_ing_2", ed1 );

		// Revision 1
		em.getTransaction().begin();

		em.persist( ed1 );
		em.persist( ed2 );

		em.persist( ing1 );
		em.persist( ing2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ing1 = em.find( SetRefIngMulIdEntity.class, ing1_id );
		ed2 = em.find( SetRefEdMulIdEntity.class, ed2_id );

		ing1.setReference( ed2 );

		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();

		ing2 = em.find( SetRefIngMulIdEntity.class, ing2_id );
		ed2 = em.find( SetRefEdMulIdEntity.class, ed2_id );

		ing2.setReference( ed2 );

		em.getTransaction().commit();
	}
