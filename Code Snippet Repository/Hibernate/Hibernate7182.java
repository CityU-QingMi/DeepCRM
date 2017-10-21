    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            Child c1 = new Child( "steve", "Hibernater" );
            Child c2 = new Child( "sally", "Joe Mama" );

            Parent p1 = new Parent( "Hibernate" );
            Parent p2 = new Parent( "Swimming" );

            c1.parent = p1;
            p1.children.add( c1 );

            c1.alternateParent = p2;
            p2.alternateChildren.add( c1 );

            c2.parent = p2;
            p2.children.add( c2 );

            c2.alternateParent = p1;
            p1.alternateChildren.add( c2 );

            s.save( p1 );
            s.save( p2 );
        } );
    }
