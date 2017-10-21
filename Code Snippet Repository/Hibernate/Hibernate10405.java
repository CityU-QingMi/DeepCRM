	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Rev 1
		em.getTransaction().begin();
		UnversionedEntity ue1 = new UnversionedEntity( "a1", "b1" );
		em.persist( ue1 );
		id1 = ue1.getId();
		em.getTransaction().commit();

		// Rev 2
		em.getTransaction().begin();
		ue1 = em.find( UnversionedEntity.class, id1 );
		ue1.setData1( "a2" );
		ue1.setData2( "b2" );
		em.getTransaction().commit();
	}
