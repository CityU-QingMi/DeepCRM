    @Before
    public void prepare() {
        ParentEntity parent = new ParentEntity();
        parent.description = "desc";
        parent.address = new Address();
        parent.address.street = "Sesame street";
        parent.address.country = new Country();
        parent.address.country.name = "Suriname";
        parent.address.country.languages = Arrays.asList( "english", "spanish" );

        parent.lazyField = new byte[100];

        doInHibernate( this::sessionFactory, s -> {
            s.persist( parent );
        } );

        checkDirtyTracking( parent );
        entityId = parent.id;
    }
