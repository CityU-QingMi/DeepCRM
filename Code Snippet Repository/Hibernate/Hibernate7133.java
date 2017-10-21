    @After
    public void cleanup() {
        doInHibernate( this::sessionFactory, s -> {
            TestEntity testEntity = s.get( TestEntity.class, "foo" );
            Assert.assertTrue( testEntity.getParams().isEmpty() );

            TestOtherEntity testOtherEntity = s.get( TestOtherEntity.class, "foo" );
            Assert.assertTrue( testOtherEntity.getParams().isEmpty() );
        } );
    }
