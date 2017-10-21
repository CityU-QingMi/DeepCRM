    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Parent parent = new Parent();
            parent.setChildren( new ArrayList<>() );
            for ( int i = 0; i < CHILDREN_SIZE; i++ ) {
                Child child = new Child();
                child.parent = parent;
                s.persist( child );
            }
            s.persist( parent );
            parentID = parent.id;
        } );
    }
