    @Test
    @FailureExpected( jiraKey = "" )
    public void testProperty() {
        doInHibernate( this::sessionFactory, s -> {
            ItemProperty input = new ItemProperty();
            input.setName( "P" );
            Map<String, String> parameters = new HashMap<>();
            parameters.put( "ccc", "CCC" );
            parameters.put( "ddd", "DDD" );
            input.setParameters( parameters );
            s.persist( input );
        } );

        doInHibernate( this::sessionFactory, s -> {
            // A parameters map is created with the class and is being compared to the persistent map (by the generated code) -- it shouldn't
            item = s.find( ItemProperty.class, "P" );
        } );

        doInHibernate( this::sessionFactory, s -> {
            mergedItem = (Item) s.merge( item );
        } );

        Assert.assertEquals( 2, mergedItem.getParameters().size() );
    }
