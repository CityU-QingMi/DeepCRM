	@Test
	@Priority(10)
	public void initData() {
		ed1_id = new EmbId( 0, 1 );
		ed2_id = new EmbId( 2, 3 );

		ing2_id = new EmbId( 4, 5 );
		ing1_id = new EmbId( 6, 7 );

		EntityManager em = getEntityManager();

		SetRefEdEmbIdEntity ed1 = new SetRefEdEmbIdEntity( ed1_id, "data_ed_1" );
		SetRefEdEmbIdEntity ed2 = new SetRefEdEmbIdEntity( ed2_id, "data_ed_2" );

		SetRefIngEmbIdEntity ing1 = new SetRefIngEmbIdEntity( ing1_id, "data_ing_1", ed1 );
		SetRefIngEmbIdEntity ing2 = new SetRefIngEmbIdEntity( ing2_id, "data_ing_2", ed1 );

		// Revision 1
		em.getTransaction().begin();

		em.persist( ed1 );
		em.persist( ed2 );

		em.persist( ing1 );
		em.persist( ing2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ing1 = em.find( SetRefIngEmbIdEntity.class, ing1.getId() );
		ed2 = em.find( SetRefEdEmbIdEntity.class, ed2.getId() );

		ing1.setReference( ed2 );

		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();

		ing2 = em.find( SetRefIngEmbIdEntity.class, ing2.getId() );
		ed2 = em.find( SetRefEdEmbIdEntity.class, ed2.getId() );

		ing2.setReference( ed2 );

		em.getTransaction().commit();
	}
