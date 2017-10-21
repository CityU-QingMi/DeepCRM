	@Before
	public void setUpData() {
		Session s = openSession();
		s.beginTransaction();
		Store store = new Store( 1 )
				.setName( "Acme Super Outlet" );
		s.persist( store );

		Product product = new Product( "007" )
				.setName( "widget" )
				.setDescription( "FooBar" );
		s.persist( product );

		store.addInventoryProduct( product )
				.setQuantity( 10L )
				.setStorePrice( new BigDecimal( 500 ) );

		s.getTransaction().commit();
		s.close();
	}
