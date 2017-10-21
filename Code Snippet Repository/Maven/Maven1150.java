    public void testCompleteDescriptor()
        throws Exception
    {
        String xml =
            "<?xml version='1.0' encoding='UTF-8'?>" + "<extension>" + "<exportedPackages>"
                + "<exportedPackage>a</exportedPackage>" + "<exportedPackage>b</exportedPackage>"
                + "<exportedPackage>c</exportedPackage>" + "</exportedPackages>" + "<exportedArtifacts>"
                + "<exportedArtifact>x</exportedArtifact>" + "<exportedArtifact>y</exportedArtifact>"
                + "<exportedArtifact> z </exportedArtifact>" + "</exportedArtifacts>" + "</extension>";

        ExtensionDescriptor ed = builder.build( toStream( xml ) );

        assertNotNull( ed );
        assertEquals( Arrays.asList( "a", "b", "c" ), ed.getExportedPackages() );
        assertEquals( Arrays.asList( "x", "y", "z" ), ed.getExportedArtifacts() );
    }
