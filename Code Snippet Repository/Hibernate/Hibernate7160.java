    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Bar bar1 = new Bar();
            Bar bar2 = new Bar();
            Foo foo = new Foo();
            s.save( bar1 );
            s.save( bar2 );
            s.save( foo );
            bar1.foo = foo;
            bar2.foo = foo;
            fooId = foo.id;
        } );
    }
