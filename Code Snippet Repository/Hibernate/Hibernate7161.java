    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Bar bar = new Bar();
            Foo foo1 = new Foo();
            Foo foo2 = new Foo();
            s.save( bar );
            s.save( foo1 );
            s.save( foo2 );

            bar.foos.add( foo1 );
            bar.foos.add( foo2 );

            myBar = bar;
        } );
    }
