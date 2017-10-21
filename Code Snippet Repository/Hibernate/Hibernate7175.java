    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Parent parent = new Parent();
            for ( int i = 0; i < CHILDREN_SIZE; i++ ) {
                Child child = new Child( "Child #" + i );
                child.parent = parent;
                parent.addChild( child );
                s.persist( child );
                lastChildID = child.id;
            }
            s.persist( parent );
            parentID = parent.id;
        } );
    }
