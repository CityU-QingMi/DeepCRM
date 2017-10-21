    @Test
    public void testField() {
        doInHibernate( this::sessionFactory, s -> {
            ItemField input = new ItemField();
            input.name = "F";
            input.parameters = new HashMap<>();
            input.parameters.put( "aaa", "AAA" );
            input.parameters.put( "bbb", "BBB" );
            s.persist( input );
        } );

        doInHibernate( this::sessionFactory, s -> {
            // A parameters map is created with the class and is being compared to the persistent map (by the generated code) -- it shouldn't
            item = s.find( ItemField.class, "F" );
        } );

        doInHibernate( this::sessionFactory, s -> {
            mergedItem = (Item) s.merge( item );
        } );

        Assert.assertEquals( 2, mergedItem.getParameters().size() );
    }
