    public void testInvalidRanges()
    {
        checkInvalidRange( "(1.0)" );
        checkInvalidRange( "[1.0)" );
        checkInvalidRange( "(1.0]" );
        checkInvalidRange( "(1.0,1.0]" );
        checkInvalidRange( "[1.0,1.0)" );
        checkInvalidRange( "(1.0,1.0)" );
        checkInvalidRange( "[1.1,1.0]" );
        checkInvalidRange( "[1.0,1.2),1.3" );
        // overlap
        checkInvalidRange( "[1.0,1.2),(1.1,1.3]" );
        // overlap
        checkInvalidRange( "[1.1,1.3),(1.0,1.2]" );
        // ordering
        checkInvalidRange( "(1.1,1.2],[1.0,1.1)" );
    }
