	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			OneToManyOwned oneToManyOwned = new OneToManyOwned( "data", null );
			ManyToOneOwned manyToOneOwned1 = new ManyToOneOwned( "data1" );
			ManyToOneOwned manyToOneOwned2 = new ManyToOneOwned( "data2" );
			ManyToManyCompositeKey owning1 = new ManyToManyCompositeKey( oneToManyOwned, manyToOneOwned1 );
			ManyToManyCompositeKey owning2 = new ManyToManyCompositeKey( oneToManyOwned, manyToOneOwned2 );

			entityManager.persist(oneToManyOwned);
			entityManager.persist(manyToOneOwned1);
			entityManager.persist(manyToOneOwned2);
			entityManager.persist( owning1 );
			entityManager.persist( owning2 );

			owning1Id = owning1.getId();
			owning2Id = owning2.getId();

			oneToManyId = oneToManyOwned.getId();
			manyToOne1Id = manyToOneOwned1.getId();
			manyToOne2Id = manyToOneOwned2.getId();
		} );

		// Revision 2
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			ManyToManyCompositeKey owning1 = entityManager.find( ManyToManyCompositeKey.class, owning1Id );
			entityManager.remove( owning1 );
		} );

		// Revision 3
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			ManyToManyCompositeKey owning2 = entityManager.find( ManyToManyCompositeKey.class, owning2Id );
			entityManager.remove( owning2 );
		} );
	}
