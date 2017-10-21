    public void testIncludesExcludesArtifactFilter()
    {
        List<String> patterns = Arrays.asList( "c", "d", "e" );

        IncludesArtifactFilter f1 = new IncludesArtifactFilter( patterns );

        IncludesArtifactFilter f2 = new IncludesArtifactFilter( patterns );

        assertTrue( f1.equals(f2) );
        assertTrue( f2.equals(f1) );
        assertTrue( f1.hashCode() == f2.hashCode() );

        IncludesArtifactFilter f3 = new IncludesArtifactFilter( Arrays.asList( "d", "c", "e" ) );
        assertTrue( f1.equals( f3 ) );
        assertTrue( f1.hashCode() == f3.hashCode() );
    }
