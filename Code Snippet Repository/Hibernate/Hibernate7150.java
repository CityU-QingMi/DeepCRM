    @Test
    public void test() {
        doInJPA( this::sessionFactory, entityManager -> {
            StringsEntity entity = entityManager.find( StringsEntity.class, 1L );
            entity.someStrings.clear();
        } );

        doInJPA( this::sessionFactory, entityManager -> {
            StringsEntity entity = entityManager.find( StringsEntity.class, 1L );
            assertEquals( 0, entity.someStrings.size() );
            entity.someStrings.add( "d" );
        } );

        doInJPA( this::sessionFactory, entityManager -> {
            StringsEntity entity = entityManager.find( StringsEntity.class, 1L );
            assertEquals( 1, entity.someStrings.size() );
            entity.someStrings = new ArrayList<>();
        } );

        doInJPA( this::sessionFactory, entityManager -> {
            StringsEntity entity = entityManager.find( StringsEntity.class, 1L );
            assertEquals( 0, entity.someStrings.size() );
        } );
    }
