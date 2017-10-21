    public void testIncompleteParent()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "incomplete-parent.xml" );

        assertViolations( result, 3, 0, 0 );

        assertTrue( result.getFatals().get( 0 ).contains( "parent.groupId" ) );
        assertTrue( result.getFatals().get( 1 ).contains( "parent.artifactId" ) );
        assertTrue( result.getFatals().get( 2 ).contains( "parent.version" ) );
    }
