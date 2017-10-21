    @Before
    public void prepare() {
        doInHibernate( this::sessionFactory, s -> {
            TestEntity testEntity = new TestEntity( "foo" );
            testEntity.setParamsAsString( "{\"paramName\":\"paramValue\"}" );
            s.persist( testEntity );

            TestOtherEntity testOtherEntity = new TestOtherEntity( "foo" );
            testOtherEntity.setParamsAsString( "{\"paramName\":\"paramValue\"}" );
            s.persist( testOtherEntity );
        } );
    }
