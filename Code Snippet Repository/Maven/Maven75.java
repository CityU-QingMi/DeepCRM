    @Test
    public void testGetLineNumber()
    {
        DefaultProblem problem = new DefaultProblem( null, null, null, -1, -1, null );
        assertEquals( -1, problem.getLineNumber() );

        problem = new DefaultProblem( null, null, null, 42, -1, null );
        assertEquals( 42, problem.getLineNumber() );
        
        problem = new DefaultProblem( null, null, null, Integer.MAX_VALUE, -1, null );
        assertEquals( Integer.MAX_VALUE, problem.getLineNumber() );

        // this case is not specified, might also return -1
        problem = new DefaultProblem( null, null, null, Integer.MIN_VALUE, -1, null );
        assertEquals( Integer.MIN_VALUE, problem.getLineNumber() );
    }
