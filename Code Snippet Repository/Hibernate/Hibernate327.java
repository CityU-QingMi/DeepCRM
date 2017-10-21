    @Test
    public void test() {

        //tag::mapping-column-many-to-any-persistence-example[]
        doInHibernate( this::sessionFactory, session -> {
            IntegerProperty ageProperty = new IntegerProperty();
            ageProperty.setId( 1L );
            ageProperty.setName( "age" );
            ageProperty.setValue( 23 );

            StringProperty nameProperty = new StringProperty();
            nameProperty.setId( 1L );
            nameProperty.setName( "name" );
            nameProperty.setValue( "John Doe" );

            session.persist( ageProperty );
            session.persist( nameProperty );

            PropertyRepository propertyRepository = new PropertyRepository();
            propertyRepository.setId( 1L );
            propertyRepository.getProperties().add( ageProperty );
            propertyRepository.getProperties().add( nameProperty );
            session.persist( propertyRepository );
        } );

        doInHibernate( this::sessionFactory, session -> {
            PropertyRepository propertyRepository = session.get( PropertyRepository.class, 1L );
            assertEquals(2, propertyRepository.getProperties().size());
            for(Property property : propertyRepository.getProperties()) {
                assertNotNull( property.getValue() );
            }
        } );
        //end::mapping-column-many-to-any-persistence-example[]
    }
