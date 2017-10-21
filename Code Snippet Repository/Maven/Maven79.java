    @Test
    public void testGetLocation()
    {
        DefaultProblem problem = new DefaultProblem( null, null, null, -1, -1, null );
        assertEquals( "", problem.getLocation() );
        
        problem = new DefaultProblem( null, null, "SOURCE", -1, -1, null );
        assertEquals( "SOURCE", problem.getLocation() );

        problem = new DefaultProblem( null, null, null, 42, -1, null );
        assertEquals( "line 42", problem.getLocation() );

        problem = new DefaultProblem( null, null, null, -1, 127, null );
        assertEquals( "column 127", problem.getLocation() );

        problem = new DefaultProblem( null, null, "SOURCE", 42, 127, null );
        assertEquals( "SOURCE, line 42, column 127", problem.getLocation() );
    }
