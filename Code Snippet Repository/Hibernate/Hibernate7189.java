    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Store store = new Store( 1L ).setName( "Acme Super Outlet" );
            s.persist( store );

            Product product = new Product( "007" ).setName( "widget" ).setDescription( "FooBar" );
            s.persist( product );

            store.addInventoryProduct( product ).setQuantity( 10L ).setStorePrice( new BigDecimal( 500 ) );
        } );
    }
