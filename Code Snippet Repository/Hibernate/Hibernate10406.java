	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		SetRefEdEntity ed1 = new SetRefEdEntity( 1, "data_ed_1" );
		SetRefEdEntity ed2 = new SetRefEdEntity( 2, "data_ed_2" );

		SetRefIngEntity ing1 = new SetRefIngEntity( 1, "data_ing_1" );
		SetRefIngEntity ing2 = new SetRefIngEntity( 2, "data_ing_2" );

		// Revision 1
		em.getTransaction().begin();

		em.persist( ed1 );
		em.persist( ed2 );

		ing1.setReference( ed1 );
		ing2.setReference( ed1 );

		em.persist( ing1 );
		em.persist( ing2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ing1 = em.find( SetRefIngEntity.class, ing1.getId() );
		ing2 = em.find( SetRefIngEntity.class, ing2.getId() );
		ed2 = em.find( SetRefEdEntity.class, ed2.getId() );

		ing1.setReference( ed2 );
		ing2.setReference( ed2 );

		em.getTransaction().commit();

		//

		ed1_id = ed1.getId();
		ed2_id = ed2.getId();

		ing1_id = ing1.getId();
		ing2_id = ing2.getId();
	}
