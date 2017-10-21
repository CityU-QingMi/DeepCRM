    @Before
    public void prepare() {
        // Create a Parent with one Child
        doInHibernate( this::sessionFactory, s -> {
                    Parent p = new Parent();
                    p.setName( "PARENT" );
                    p.setLazy( "LAZY" );
                    p.makeChild();
                    s.persist( p );
                }
        );
    }
