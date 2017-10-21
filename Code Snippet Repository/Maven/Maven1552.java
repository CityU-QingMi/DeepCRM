    public void testInterpolateObjectWithUnmodifiableStringListField()
        throws Exception
    {
        Model model = new Model();

        Properties p = new Properties();
        p.setProperty( "key", "value" );
        p.setProperty( "key2", "value2" );

        List<String> values = Collections.unmodifiableList( Collections.singletonList( "${key}" ) );

        ObjectWithListField obj = new ObjectWithListField( values );

        StringSearchModelInterpolator interpolator = (StringSearchModelInterpolator) createInterpolator();

        ModelBuildingRequest config = createModelBuildingRequest( p );

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        interpolator.interpolateObject( obj, model, new File( "." ), config, collector );
        assertProblemFree( collector );

        assertEquals( "${key}", obj.values.get( 0 ) );
    }
