	@Test
	@Priority(10)
	public void initData() {
		ed_id1 = new MulIdNaming( 10, 20 );
		ed_id2 = new MulIdNaming( 11, 21 );
		ing_id1 = new MulIdNaming( 12, 22 );

		JoinMulIdNamingRefEdEntity ed1 = new JoinMulIdNamingRefEdEntity( ed_id1, "data1" );
		JoinMulIdNamingRefEdEntity ed2 = new JoinMulIdNamingRefEdEntity( ed_id2, "data2" );

		JoinMulIdNamingRefIngEntity ing1 = new JoinMulIdNamingRefIngEntity( ing_id1, "x", ed1 );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		em.persist( ed1 );
		em.persist( ed2 );
		em.persist( ing1 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		ed2 = em.find( JoinMulIdNamingRefEdEntity.class, ed_id2 );

		ing1 = em.find( JoinMulIdNamingRefIngEntity.class, ing_id1 );
		ing1.setData( "y" );
		ing1.setReference( ed2 );

		em.getTransaction().commit();
	}
