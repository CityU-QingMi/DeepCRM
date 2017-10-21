    public void testEmptyDescriptor()
        throws Exception
    {
        String xml = "<extension></extension>";

        ExtensionDescriptor ed = builder.build( toStream( xml ) );

        assertNotNull( ed );
        assertNotNull( ed.getExportedPackages() );
        assertTrue( ed.getExportedPackages().isEmpty() );
        assertNotNull( ed.getExportedArtifacts() );
        assertTrue( ed.getExportedArtifacts().isEmpty() );
    }
