    public void testDuplicatePluginExecution()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "duplicate-plugin-execution.xml" );

        assertViolations( result, 0, 4, 0 );

        assertContains( result.getErrors().get( 0 ), "duplicate execution with id a" );
        assertContains( result.getErrors().get( 1 ), "duplicate execution with id default" );
        assertContains( result.getErrors().get( 2 ), "duplicate execution with id c" );
        assertContains( result.getErrors().get( 3 ), "duplicate execution with id b" );
    }
