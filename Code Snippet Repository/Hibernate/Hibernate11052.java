	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		PrimitiveTestEntity pte = new PrimitiveTestEntity( 10, 11 );
		em.persist( pte );
		id1 = pte.getId();
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		pte = em.find( PrimitiveTestEntity.class, id1 );
		pte.setNumVal1( 20 );
		pte.setNumVal2( 21 );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		pte = em.find( PrimitiveTestEntity.class, id1 );
		em.remove( pte );
		em.getTransaction().commit();
	}
