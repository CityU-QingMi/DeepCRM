	@Test
	@Priority(10)
	public void initData() {
		ed_id1 = new EmbIdNaming( 10, 20 );
		ed_id2 = new EmbIdNaming( 11, 21 );
		ing_id1 = new EmbIdNaming( 12, 22 );

		JoinEmbIdNamingRefEdEntity ed1 = new JoinEmbIdNamingRefEdEntity( ed_id1, "data1" );
		JoinEmbIdNamingRefEdEntity ed2 = new JoinEmbIdNamingRefEdEntity( ed_id2, "data2" );

		JoinEmbIdNamingRefIngEntity ing1 = new JoinEmbIdNamingRefIngEntity( ing_id1, "x", ed1 );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		em.persist( ed1 );
		em.persist( ed2 );
		em.persist( ing1 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ed2 = em.find( JoinEmbIdNamingRefEdEntity.class, ed2.getId() );

		ing1 = em.find( JoinEmbIdNamingRefIngEntity.class, ing1.getId() );
		ing1.setData( "y" );
		ing1.setReference( ed2 );

		em.getTransaction().commit();
	}
