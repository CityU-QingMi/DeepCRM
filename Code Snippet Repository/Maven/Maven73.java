    @Test
    public void testSetSource()
    {
        DefaultProblemCollector collector = new DefaultProblemCollector( null );
        
        collector.add( null, "PROBLEM1", -1, -1, null );

        collector.setSource( "SOURCE_PROBLEM2" );
        collector.add( null, "PROBLEM2", -1, -1, null );

        collector.setSource( "SOURCE_PROBLEM3" );
        collector.add( null, "PROBLEM3", -1, -1, null );

        assertEquals( "", collector.getProblems().get( 0 ).getSource() );
        assertEquals( "SOURCE_PROBLEM2", collector.getProblems().get( 1 ).getSource() );
        assertEquals( "SOURCE_PROBLEM3", collector.getProblems().get( 2 ).getSource() );
    }
