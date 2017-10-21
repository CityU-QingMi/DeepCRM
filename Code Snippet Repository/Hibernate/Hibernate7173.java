    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Parent parent = new Parent();
            for ( int i = 0; i < CHILDREN_SIZE; i++ ) {
                Child child = new Child();
                // Association management should kick in here
                child.parent = parent;
                s.persist( child );
                lastChildID = child.id;
            }
            s.persist( parent );
        } );
    }
