    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Child child = new Child();
            child.setId( 2L );
            s.save( child );

            Parent parent = new Parent();
            parent.setId( 1L );
            parent.setNames( Collections.singleton( "name" ) );
            parent.addChild( child );

            s.save( parent );
        } );
    }
