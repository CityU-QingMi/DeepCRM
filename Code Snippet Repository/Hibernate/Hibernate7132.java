    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            TestEntity testEntity = s.get( TestEntity.class, "foo" );
            Assert.assertEquals( "{\"paramName\":\"paramValue\"}", testEntity.getParamsAsString() );

            TestOtherEntity testOtherEntity = s.get( TestOtherEntity.class, "foo" );
            Assert.assertEquals( "{\"paramName\":\"paramValue\"}", testOtherEntity.getParamsAsString() );

            // Clean parameters
            cleanup = true;
            testEntity.setParamsAsString( "{}" );
            testOtherEntity.setParamsAsString( "{}" );
        } );
    }
