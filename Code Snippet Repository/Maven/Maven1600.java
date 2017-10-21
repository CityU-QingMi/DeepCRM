    public void testSelfReferencingDependencyInRawModel()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "raw-model/self-referencing.xml" );

        assertViolations( result, 1, 0, 0 );

        assertEquals( "'dependencies.dependency com.example.group:testinvalidpom:0.0.1-SNAPSHOT' for com.example.group:testinvalidpom:0.0.1-SNAPSHOT is referencing itself.",
                      result.getFatals().get( 0 ) );

    }
