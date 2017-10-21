    public void testFullInterpolationOfNestedExpressions()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "full-interpolation" );
        for ( int i = 0; i < 24; i++ )
        {
            String index = ( ( i < 10 ) ? "0" : "" ) + i;
            assertEquals( "PASSED", pom.getValue( "properties/property" + index ) );
        }
    }
