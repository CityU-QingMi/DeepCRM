    public void testMergedFilterOrder()
        throws Exception
    {
        PomTestWrapper pom = buildPom( "merged-filter-order/sub" );

        assertEquals( 7, ( (List<?>) pom.getValue( "build/filters" ) ).size() );
        assertTrue( pom.getValue( "build/filters[1]" ).toString().endsWith( "child-a.properties" ) );
        assertTrue( pom.getValue( "build/filters[2]" ).toString().endsWith( "child-c.properties" ) );
        assertTrue( pom.getValue( "build/filters[3]" ).toString().endsWith( "child-b.properties" ) );
        assertTrue( pom.getValue( "build/filters[4]" ).toString().endsWith( "child-d.properties" ) );
        assertTrue( pom.getValue( "build/filters[5]" ).toString().endsWith( "parent-c.properties" ) );
        assertTrue( pom.getValue( "build/filters[6]" ).toString().endsWith( "parent-b.properties" ) );
        assertTrue( pom.getValue( "build/filters[7]" ).toString().endsWith( "parent-d.properties" ) );
    }
