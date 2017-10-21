    @Test
    public void testGetSeverity()
    {
        DefaultProblem problem = new DefaultProblem( null, null, null, -1, -1, null );
        assertEquals( Severity.ERROR, problem.getSeverity() );

        problem = new DefaultProblem( null, Severity.FATAL, null, -1, -1, null );
        assertEquals( Severity.FATAL, problem.getSeverity() );
        
        problem = new DefaultProblem( null, Severity.ERROR, null, -1, -1, null );
        assertEquals( Severity.ERROR, problem.getSeverity() );

        problem = new DefaultProblem( null, Severity.WARNING, null, -1, -1, null );
        assertEquals( Severity.WARNING, problem.getSeverity() );
    }
