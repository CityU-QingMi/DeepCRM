    @Before
    public void prepare() {
        doInJPA( this::sessionFactory, em -> {
            Child c = new Child();
            em.persist( c );

            Parent parent = new Parent();
            parent.setChild( c );
            em.persist( parent );
            parentID = parent.getId();
        } );
    }
