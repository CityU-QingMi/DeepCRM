	@Test
	@Priority(10)
	public void initData() {
		// No revision
		EntityManager em = getEntityManager();
		em.getTransaction().begin();

		UnversionedStrTestEntity ste1 = new UnversionedStrTestEntity();
		ste1.setStr( "str1" );

		UnversionedStrTestEntity ste2 = new UnversionedStrTestEntity();
		ste2.setStr( "str2" );

		em.persist( ste1 );
		em.persist( ste2 );

		em.getTransaction().commit();

		// Revision 1
		em = getEntityManager();
		em.getTransaction().begin();

		NotAuditedManyToOneComponentTestEntity mtocte1 = new NotAuditedManyToOneComponentTestEntity(
				new NotAuditedManyToOneComponent( ste1, "data1" )
		);

		em.persist( mtocte1 );

		em.getTransaction().commit();

		// No revision
		em = getEntityManager();
		em.getTransaction().begin();

		mtocte1 = em.find( NotAuditedManyToOneComponentTestEntity.class, mtocte1.getId() );
		mtocte1.getComp1().setEntity( ste2 );

		em.getTransaction().commit();

		// Revision 2
		em = getEntityManager();
		em.getTransaction().begin();

		mtocte1 = em.find( NotAuditedManyToOneComponentTestEntity.class, mtocte1.getId() );
		mtocte1.getComp1().setData( "data2" );

		em.getTransaction().commit();

		mtocte_id1 = mtocte1.getId();
	}
