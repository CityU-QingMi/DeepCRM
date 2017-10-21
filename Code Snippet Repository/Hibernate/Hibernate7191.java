    @After
    public void cleanup() throws Exception {
        doInHibernate( this::sessionFactory, s -> {
            Store store = s.find( Store.class, 1L );
            s.delete( store );

            Product product= s.find( Product.class, "007" );
            s.delete( product );
        } );
    }
