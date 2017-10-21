    public void testBuildParentVersionRangeExternally() throws Exception
    {
        File f1 = getTestFile( "src/test/resources/projects/parent-version-range-external-valid/pom.xml" );

        final MavenProject childProject = this.getProjectFromRemoteRepository( f1 );

        assertNotNull( childProject.getParentArtifact() );
        assertEquals( childProject.getParentArtifact().getVersion(), "1" );
        assertNotNull( childProject.getParent() );
        assertEquals( childProject.getParent().getVersion(), "1" );
        assertNotNull( childProject.getModel().getParent() );
        assertEquals( childProject.getModel().getParent().getVersion(), "[1,1]" );
    }
