	@Test
	@Priority(10)
	public void initData() throws InterruptedException {
		timestamp1 = System.currentTimeMillis();

		Thread.sleep( 100 );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		StrTestEntity rfd = new StrTestEntity( "x" );
		em.persist( rfd );
		id = rfd.getId();
		em.getTransaction().commit();

		timestamp2 = System.currentTimeMillis();

		Thread.sleep( 100 );

		// Revision 2
		em.getTransaction().begin();
		rfd = em.find( StrTestEntity.class, id );
		rfd.setStr( "y" );
		em.getTransaction().commit();

		timestamp3 = System.currentTimeMillis();

		Thread.sleep( 100 );

		// Revision 3
		em.getTransaction().begin();
		rfd = em.find( StrTestEntity.class, id );
		rfd.setStr( "z" );
		em.getTransaction().commit();

		timestamp4 = System.currentTimeMillis();
	}
