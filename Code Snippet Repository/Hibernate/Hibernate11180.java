	@Test
	@Priority(10)
	public void initData() throws InterruptedException {
		timestamp1 = System.currentTimeMillis();

		Thread.sleep( 1100 ); // CustomDateRevEntity.dateTimestamp field maps to date type which on some RDBMSs gets
		// truncated to seconds (for example MySQL 5.1).

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		StrTestEntity te = new StrTestEntity( "x" );
		em.persist( te );
		id = te.getId();
		em.getTransaction().commit();

		timestamp2 = System.currentTimeMillis();

		Thread.sleep( 1100 ); // CustomDateRevEntity.dateTimestamp field maps to date type which on some RDBMSs gets
		// truncated to seconds (for example MySQL 5.1).

		// Revision 2
		em.getTransaction().begin();
		te = em.find( StrTestEntity.class, id );
		te.setStr( "y" );
		em.getTransaction().commit();

		timestamp3 = System.currentTimeMillis();
	}
