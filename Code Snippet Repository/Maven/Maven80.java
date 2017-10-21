    @Test
    public void testGetMessage()
    {
        DefaultProblem problem = new DefaultProblem( "MESSAGE", null, null, -1, -1, null );
        assertEquals( "MESSAGE", problem.getMessage() );

        problem = new DefaultProblem( null, null, null, -1, -1, new Exception() );
        assertEquals( "", problem.getMessage() );

        problem = new DefaultProblem( null, null, null, -1, -1, new Exception( "EXCEPTION MESSAGE" ) );
        assertEquals( "EXCEPTION MESSAGE", problem.getMessage() );
    }
