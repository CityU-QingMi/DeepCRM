	@Test
	@Priority(10)
	public void initData() {
		id1 = new EmbId( 0, 1 );
		id2 = new EmbId( 10, 11 );
		id3 = new EmbId( 20, 21 );
		id4 = new EmbId( 30, 31 );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		SetRefIngEmbIdEntity refIng1 = new SetRefIngEmbIdEntity( id1, "x", null );
		SetRefIngEmbIdEntity refIng2 = new SetRefIngEmbIdEntity( id2, "y", null );

		em.persist( refIng1 );
		em.persist( refIng2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		SetRefEdEmbIdEntity refEd3 = new SetRefEdEmbIdEntity( id3, "a" );
		SetRefEdEmbIdEntity refEd4 = new SetRefEdEmbIdEntity( id4, "a" );

		em.persist( refEd3 );
		em.persist( refEd4 );

		refIng1 = em.find( SetRefIngEmbIdEntity.class, id1 );
		refIng2 = em.find( SetRefIngEmbIdEntity.class, id2 );

		refIng1.setReference( refEd3 );
		refIng2.setReference( refEd4 );

		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();

		refEd3 = em.find( SetRefEdEmbIdEntity.class, id3 );
		refIng2 = em.find( SetRefIngEmbIdEntity.class, id2 );
		refIng2.setReference( refEd3 );

		em.getTransaction().commit();
	}
