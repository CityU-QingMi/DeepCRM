    public void testEquals()
    {
        AndArtifactFilter filter1 = new AndArtifactFilter();

        AndArtifactFilter filter2 = new AndArtifactFilter( Arrays.asList( newSubFilter() ) );

        assertFalse( filter1.equals( null ) );
        assertTrue( filter1.equals( filter1 ) );
        assertEquals( filter1.hashCode(), filter1.hashCode() );

        assertFalse( filter1.equals( filter2 ) );
        assertFalse( filter2.equals( filter1 ) );
    }
