	@Test
	@Priority(10)
	public void initData() throws InterruptedException {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		StrIntTestEntity site1 = new StrIntTestEntity( "a", 10 );
		StrIntTestEntity site2 = new StrIntTestEntity( "b", 15 );

		em.persist( site1 );
		em.persist( site2 );

		id1 = site1.getId();
		id2 = site2.getId();

		em.getTransaction().commit();

		Thread.sleep( 100 );

		timestamp = System.currentTimeMillis();

		Thread.sleep( 100 );

		// Revision 2
		em.getTransaction().begin();

		site1 = em.find( StrIntTestEntity.class, id1 );

		site1.setStr1( "c" );

		em.getTransaction().commit();
	}
