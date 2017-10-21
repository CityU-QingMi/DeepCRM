	@Test
	@Priority(10)
	public void initData() {
		ed1_id = new EmbId( 1, 2 );
		ed2_id = new EmbId( 3, 4 );

		ing1_id = new EmbId( 5, 6 );

		BiEmbIdRefEdEntity ed1 = new BiEmbIdRefEdEntity( ed1_id, "data_ed_1" );
		BiEmbIdRefEdEntity ed2 = new BiEmbIdRefEdEntity( ed2_id, "data_ed_2" );

		BiEmbIdRefIngEntity ing1 = new BiEmbIdRefIngEntity( ing1_id, "data_ing_1" );

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

		ing1 = em.find( BiEmbIdRefIngEntity.class, ing1.getId() );
		ed2 = em.find( BiEmbIdRefEdEntity.class, ed2.getId() );

		ing1.setReference( ed2 );

		em.getTransaction().commit();
	}
