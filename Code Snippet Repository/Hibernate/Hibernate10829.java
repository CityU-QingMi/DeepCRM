	@Test
	@Priority(10)
	public void initData() {
		JoinNamingRefEdEntity ed1 = new JoinNamingRefEdEntity( "data1" );
		JoinNamingRefEdEntity ed2 = new JoinNamingRefEdEntity( "data2" );

		JoinNamingRefIngEntity ing1 = new JoinNamingRefIngEntity( "x", ed1 );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		em.persist( ed1 );
		em.persist( ed2 );
		em.persist( ing1 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ed2 = em.find( JoinNamingRefEdEntity.class, ed2.getId() );

		ing1 = em.find( JoinNamingRefIngEntity.class, ing1.getId() );
		ing1.setData( "y" );
		ing1.setReference( ed2 );

		em.getTransaction().commit();

		//

		ed_id1 = ed1.getId();
		ed_id2 = ed2.getId();
		ing_id1 = ing1.getId();
	}
