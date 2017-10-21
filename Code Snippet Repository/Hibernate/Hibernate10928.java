	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		SetRefEdEntity ed1 = new SetRefEdEntity( 1, "data_ed_1" );

		SetRefIngEntity ing1 = new SetRefIngEntity( 3, "data_ing_1" );

		// Revision 1
		em.getTransaction().begin();

		em.persist( ed1 );

		em.getTransaction().commit();

		// Revision 2

		em.getTransaction().begin();

		ed1 = em.find( SetRefEdEntity.class, ed1.getId() );

		em.persist( ing1 );

		ed1.setReffering( new HashSet<SetRefIngEntity>() );
		ed1.getReffering().add( ing1 );

		em.getTransaction().commit();

		//

		ed1_id = ed1.getId();

		ing1_id = ing1.getId();
	}
