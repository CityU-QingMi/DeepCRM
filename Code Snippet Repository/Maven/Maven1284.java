    public void testCreateVersionMatcher()
    {
        RequirementMatcher matcher;
        matcher = RequirementMatcherFactory.createVersionMatcher( "1.5.2" );
        assertFalse( matcher.matches( "1.5" ) );
        assertTrue( matcher.matches( "1.5.2" ) );
        assertFalse( matcher.matches( "[1.4,1.5)" ) );
        assertFalse( matcher.matches( "[1.5,1.5.2)" ) );
        assertFalse( matcher.matches( "(1.5.2,1.6)" ) );
        assertTrue( matcher.matches( "(1.4,1.5.2]" ) );
        assertTrue( matcher.matches( "(1.5,)" ) );
        assertEquals( "1.5.2", matcher.toString() );

        // Ensure it is not printed as 1.5.0
        matcher = RequirementMatcherFactory.createVersionMatcher( "1.5" );
        assertEquals( "1.5", matcher.toString() );

    }
