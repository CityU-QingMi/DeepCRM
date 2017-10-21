    @Test
    public void test() {

        //tag::mapping-column-any-persistence-example[]
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

            PropertyHolder namePropertyHolder = new PropertyHolder();
            namePropertyHolder.setId( 1L );
            namePropertyHolder.setProperty( nameProperty );
            session.persist( namePropertyHolder );
        } );

        doInHibernate( this::sessionFactory, session -> {
            PropertyHolder propertyHolder = session.get( PropertyHolder.class, 1L );
            assertEquals("name", propertyHolder.getProperty().getName());
            assertEquals("John Doe", propertyHolder.getProperty().getValue());
        } );
        //end::mapping-column-any-persistence-example[]
    }
