    public void testInvalidIds()
        throws Exception
    {
        SimpleProblemCollector result = validate( "invalid-ids-pom.xml" );

        assertViolations( result, 0, 2, 0 );

        assertEquals( "'groupId' with value 'o/a/m' does not match a valid id pattern.", result.getErrors().get( 0 ) );

        assertEquals( "'artifactId' with value 'm$-do$' does not match a valid id pattern.",
                      result.getErrors().get( 1 ) );
    }
