    public void testBadImportScopeClassifier()
        throws Exception
    {
        SimpleProblemCollector result = validateRaw( "bad-import-scope-classifier.xml" );

        assertViolations( result, 0, 1, 0 );

        assertContains( result.getErrors().get( 0 ),
                        "'dependencyManagement.dependencies.dependency.classifier' for test:a:pom:cls must be empty" );
    }
