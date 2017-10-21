    public void testBadModelVersion()
        throws Exception
    {
        SimpleProblemCollector result =
            validateRaw( "bad-modelVersion.xml", ModelBuildingRequest.VALIDATION_LEVEL_STRICT );

        assertViolations( result, 0, 1, 0 );

        assertTrue( result.getErrors().get( 0 ).contains( "modelVersion" ) );
    }
