    @Test
    public void testGetProblems()
    {
        DefaultProblemCollector collector = new DefaultProblemCollector( null );
        assertNotNull( collector.getProblems() );
        assertEquals( 0, collector.getProblems().size() );

        collector.add( null, "MESSAGE1", -1, -1, null );
        
        Exception e2 = new Exception();
        collector.add( Severity.WARNING, null, 42, 127, e2 );
        
        assertEquals( 2, collector.getProblems().size() );

        Problem p1 = collector.getProblems().get(0);
        assertEquals( Severity.ERROR, p1.getSeverity() );
        assertEquals( "MESSAGE1",p1.getMessage() );
        assertEquals( -1, p1.getLineNumber() );
        assertEquals( -1, p1.getColumnNumber() );
        assertEquals( null, p1.getException() );
        
        Problem p2 = collector.getProblems().get(1);
        assertEquals( Severity.WARNING, p2.getSeverity() );
        assertEquals( "",p2.getMessage() );
        assertEquals( 42, p2.getLineNumber() );
        assertEquals( 127, p2.getColumnNumber() );
        assertEquals( e2, p2.getException() );
    }
