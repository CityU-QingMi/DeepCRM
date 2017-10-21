    @Before
    public void prepare() {
        Order order = new Order();
        Product product = new Product();
        order.products.add( product );
        order.data = "some data".getBytes( Charset.defaultCharset() );

        doInJPA( this::sessionFactory, em -> {
            em.persist( product );
            em.persist( order );
        } );

        orderId = order.id;
    }
