    public void testInterpolateObjectWithStringArrayListField()
        throws Exception
    {
        Model model = new Model();

        Properties p = new Properties();
        p.setProperty( "key", "value" );
        p.setProperty( "key2", "value2" );
        p.setProperty( "key3", "value3" );
        p.setProperty( "key4", "value4" );

        List<String[]> values = new ArrayList<>();
        values.add( new String[] { "${key}", "${key2}" } );
        values.add( new String[] { "${key3}", "${key4}" } );

        ObjectWithListField obj = new ObjectWithListField( values );

        StringSearchModelInterpolator interpolator = (StringSearchModelInterpolator) createInterpolator();

        ModelBuildingRequest config = createModelBuildingRequest( p );

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        interpolator.interpolateObject( obj, model, new File( "." ), config, collector );
        assertProblemFree( collector );

        assertEquals( "value", ( (String[]) obj.values.get( 0 ) )[0] );
        assertEquals( "value2", ( (String[]) obj.values.get( 0 ) )[1] );
        assertEquals( "value3", ( (String[]) obj.values.get( 1 ) )[0] );
        assertEquals( "value4", ( (String[]) obj.values.get( 1 ) )[1] );
    }
