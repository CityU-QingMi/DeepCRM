	@Override
	protected void prepareTest() throws Exception {

		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.createQuery( "DELETE FROM PurchaseItem" ).executeUpdate();
			session.createQuery( "DELETE FROM PurchaseOrder" ).executeUpdate();
			session.flush();
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			PurchaseOrder order1 = new PurchaseOrder( 1L, 10L, 1000L );
			Set<PurchaseItem> items1 = new HashSet<>();
			items1.add( new PurchaseItem( 1L, 100L, order1 ) );
			items1.add( new PurchaseItem( 2L, 200L, order1 ) );
			order1.setPurchaseItems( items1 );
			session.persist( order1 );

			PurchaseOrder order2 = new PurchaseOrder( 2L, 20L, 2000L );
			Set<PurchaseItem> items2 = new HashSet<>();
			items2.add( new PurchaseItem( 3L, 300L, order2 ) );
			items2.add( new PurchaseItem( 4L, 400L, order2 ) );
			order2.setPurchaseItems( items2 );
			session.persist( order2 );
		} );
	}
