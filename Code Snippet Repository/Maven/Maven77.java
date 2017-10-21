    @Test
    public void testGetException()
    {
        DefaultProblem problem = new DefaultProblem( null, null, null, -1, -1, null );
        assertEquals( null, problem.getException() );
        
        Exception e = new Exception();
        problem = new DefaultProblem( null, null, null, -1, -1, e );
        assertSame( e, problem.getException() );
    }
