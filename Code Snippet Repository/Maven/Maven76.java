    @Test
    public void testGetColumnNumber()
    {
        DefaultProblem problem = new DefaultProblem( null, null, null, -1, -1, null );
        assertEquals( -1, problem.getColumnNumber() );

        problem = new DefaultProblem( null, null, null, -1, 42, null );
        assertEquals( 42, problem.getColumnNumber() );
        
        problem = new DefaultProblem( null, null, null, -1, Integer.MAX_VALUE, null );
        assertEquals( Integer.MAX_VALUE, problem.getColumnNumber() );

        // this case is not specified, might also return -1
        problem = new DefaultProblem( null, null, null, -1, Integer.MIN_VALUE, null );
        assertEquals( Integer.MIN_VALUE, problem.getColumnNumber() );
    }
