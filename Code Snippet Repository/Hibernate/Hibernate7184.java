    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            TestEntity entity = s.load( TestEntity.class, 1L );
            assertLoaded( entity, "name" );
            assertNotLoaded( entity, "lifeStory" );
            assertNotLoaded( entity, "reallyBigString" );

            entity.lifeStory = "blah blah blah";
            assertLoaded( entity, "name" );
            assertLoaded( entity, "lifeStory" );
            assertNotLoaded( entity, "reallyBigString" );
        } );

        doInHibernate( this::sessionFactory, s -> {
            TestEntity entity = s.load( TestEntity.class, 1L );

            assertLoaded( entity, "name" );
            assertNotLoaded( entity, "lifeStory" );
            assertNotLoaded( entity, "reallyBigString" );
            assertEquals( "blah blah blah", entity.lifeStory );
            assertEquals( REALLY_BIG_STRING, entity.reallyBigString );
        } );
    }
