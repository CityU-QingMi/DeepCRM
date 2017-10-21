	@Test
	@Priority(10)
	public void initData() {
		id1 = new MulId( 0, 1 );
		id2 = new MulId( 10, 11 );
		id3 = new MulId( 20, 21 );
		id4 = new MulId( 30, 31 );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		SetRefIngMulIdEntity refIng1 = new SetRefIngMulIdEntity( id1, "x", null );
		SetRefIngMulIdEntity refIng2 = new SetRefIngMulIdEntity( id2, "y", null );

		em.persist( refIng1 );
		em.persist( refIng2 );

		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();

		SetRefEdMulIdEntity refEd3 = new SetRefEdMulIdEntity( id3, "a" );
		SetRefEdMulIdEntity refEd4 = new SetRefEdMulIdEntity( id4, "a" );

		em.persist( refEd3 );
		em.persist( refEd4 );

		refIng1 = em.find( SetRefIngMulIdEntity.class, id1 );
		refIng2 = em.find( SetRefIngMulIdEntity.class, id2 );

		refIng1.setReference( refEd3 );
		refIng2.setReference( refEd4 );

		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();

		refEd3 = em.find( SetRefEdMulIdEntity.class, id3 );
		refIng2 = em.find( SetRefIngMulIdEntity.class, id2 );
		refIng2.setReference( refEd3 );

		em.getTransaction().commit();
	}
