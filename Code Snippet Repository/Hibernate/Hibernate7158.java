    @Before
    public void prepare() {
        persister = sessionFactory().getMetamodel().entityPersister( Document.class );
        assertTrue( persister.hasCache() );

        doInHibernate( this::sessionFactory, s -> {
            Document document = new Document( "HiA", "Hibernate book", "Hibernate is...." );
            s.persist( document );
            documentID = document.id;
        } );
    }
