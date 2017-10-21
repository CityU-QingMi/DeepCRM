    @Test
    public void testNewInstance()
    {
        ProblemCollector collector1 = ProblemCollectorFactory.newInstance( null );
        
        Problem problem = new DefaultProblem( "MESSAGE1", null, null, -1, -1, null );
        ProblemCollector collector2 = ProblemCollectorFactory.newInstance( Collections.singletonList( problem ) );
        
        assertNotSame( collector1, collector2 );
        assertEquals( 0, collector1.getProblems().size() );
        assertEquals( 1, collector2.getProblems().size() );
    }
