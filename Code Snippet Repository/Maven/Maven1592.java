    public void testBadImportScopeType()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "bad-import-scope-type.xml" );

        assertViolations( result, 0, 0, 1 );

        assertContains( result.getWarnings().get( 0 ),
                        "'dependencyManagement.dependencies.dependency.type' for test:a:jar must be 'pom'" );
    }
