    public void testInterpolateObjectWithStringListFieldAndOneLiteralValue()
        throws Exception
    {
        Model model = new Model();

        Properties p = new Properties();
        p.setProperty( "key", "value" );
        p.setProperty( "key2", "value2" );

        List<String> values = new ArrayList<>();
        values.add( "key" );
        values.add( "${key2}" );

        ObjectWithListField obj = new ObjectWithListField( values );

        StringSearchModelInterpolator interpolator = (StringSearchModelInterpolator) createInterpolator();

        ModelBuildingRequest config = createModelBuildingRequest( p );

        final SimpleProblemCollector collector = new SimpleProblemCollector();
        interpolator.interpolateObject( obj, model, new File( "." ), config, collector );
        assertProblemFree( collector );

        assertEquals( "key", obj.values.get( 0 ) );
        assertEquals( "value2", obj.values.get( 1 ) );
    }
