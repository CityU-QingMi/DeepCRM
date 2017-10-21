    @Test
    public void testBuildEmptyRequest()
        throws Exception
    {
        ToolchainsBuildingRequest request = new DefaultToolchainsBuildingRequest();
        ToolchainsBuildingResult result = toolchainBuilder.build( request );
        assertNotNull( result.getEffectiveToolchains() );
        assertNotNull( result.getProblems() );
        assertEquals( 0, result.getProblems().size() );
    }
