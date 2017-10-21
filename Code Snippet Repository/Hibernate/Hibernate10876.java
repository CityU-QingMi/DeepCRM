	@Test
	@Priority(10)
	public void initData() {
		QuotedFieldsEntity qfe1 = new QuotedFieldsEntity( "data1", 1 );
		QuotedFieldsEntity qfe2 = new QuotedFieldsEntity( "data2", 2 );

		// Revision 1
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist( qfe1 );
		em.persist( qfe2 );
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		qfe1 = em.find( QuotedFieldsEntity.class, qfe1.getId() );
		qfe1.setData1( "data1 changed" );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		qfe2 = em.find( QuotedFieldsEntity.class, qfe2.getId() );
		qfe2.setData2( 3 );
		em.getTransaction().commit();

		qfeId1 = qfe1.getId();
		qfeId2 = qfe2.getId();
	}
