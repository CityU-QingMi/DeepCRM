	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		CountingRevisionListener.revisionCount = 0;

		// Revision 1
		em.getTransaction().begin();
		StrTestEntity te = new StrTestEntity( "data" );
		em.persist( te );
		em.getTransaction().commit();

		Assert.assertEquals( 1, CountingRevisionListener.revisionCount );
	}
