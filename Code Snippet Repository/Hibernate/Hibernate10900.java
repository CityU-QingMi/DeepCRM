	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		ListRefEdEntity ed1 = new ListRefEdEntity( 1, "data_ed_1" );
		ListRefEdEntity ed2 = new ListRefEdEntity( 2, "data_ed_2" );

		ListRefIngEntity ing1 = new ListRefIngEntity( 3, "data_ing_1", ed1 );
		ListRefIngEntity ing2 = new ListRefIngEntity( 4, "data_ing_2", ed1 );

		// Revision 1
		em.getTransaction().begin();

		em.persist( ed1 );
		em.persist( ed2 );

		em.persist( ing1 );
		em.persist( ing2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ing1 = em.find( ListRefIngEntity.class, ing1.getId() );
		ed2 = em.find( ListRefEdEntity.class, ed2.getId() );

		ing1.setReference( ed2 );

		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();

		ing2 = em.find( ListRefIngEntity.class, ing2.getId() );
		ed2 = em.find( ListRefEdEntity.class, ed2.getId() );

		ing2.setReference( ed2 );

		em.getTransaction().commit();

		//

		ed1_id = ed1.getId();
		ed2_id = ed2.getId();

		ing1_id = ing1.getId();
		ing2_id = ing2.getId();
	}
