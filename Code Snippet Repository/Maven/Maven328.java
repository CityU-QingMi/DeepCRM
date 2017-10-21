    public void testEquals()
    {
        OrArtifactFilter filter1 = new OrArtifactFilter();

        OrArtifactFilter filter2 = new OrArtifactFilter( Arrays.asList( newSubFilter() ) );

        assertFalse( filter1.equals( null ) );
        assertTrue( filter1.equals( filter1 ) );
        assertEquals( filter1.hashCode(), filter1.hashCode() );

        assertFalse( filter1.equals( filter2 ) );
        assertFalse( filter2.equals( filter1 ) );
    }
