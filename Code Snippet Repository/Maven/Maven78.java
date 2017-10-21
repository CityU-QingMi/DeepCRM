    @Test
    public void testGetSource()
    {
        DefaultProblem problem = new DefaultProblem( null, null, null, -1, -1, null );
        assertEquals( "", problem.getSource() );
        
        problem = new DefaultProblem( null, null, "", -1, -1, null );
        assertEquals( "", problem.getSource() );

        problem = new DefaultProblem( null, null, "SOURCE", -1, -1, null );
        assertEquals( "SOURCE", problem.getSource() );
    }
