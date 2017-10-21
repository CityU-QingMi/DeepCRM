    @Test
    public void testUnknownPosition()
    {
        ProblemCollector problemCollector = ProblemCollectorFactory.newInstance( null );
        problemCollector.setSource( "SOURCE" );
        problemCollector.add( Problem.Severity.ERROR, "MESSAGE", -1, -1, new Exception() );
        ToolchainsBuildingException e = new ToolchainsBuildingException( problemCollector.getProblems() );
        assertEquals( "1 problem was encountered while building the effective toolchains" + LS +
                      "[ERROR] MESSAGE @ SOURCE" + LS, e.getMessage() );
    }
