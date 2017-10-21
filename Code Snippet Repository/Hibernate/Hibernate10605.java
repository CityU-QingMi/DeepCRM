	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		UnversionedStrTestEntity uste = new UnversionedStrTestEntity();
		uste.setStr( "test1" );
		em.persist( uste );

		id1 = new ManyToOneNotAuditedEmbId( uste );

		em.getTransaction().commit();

		// Revision 2
		em = getEntityManager();
		em.getTransaction().begin();

		ManyToOneIdNotAuditedTestEntity mtoinate = new ManyToOneIdNotAuditedTestEntity();
		mtoinate.setData( "data1" );
		mtoinate.setId( id1 );
		em.persist( mtoinate );

		em.getTransaction().commit();
	}
