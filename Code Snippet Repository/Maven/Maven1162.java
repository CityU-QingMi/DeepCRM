    public void testCloneWithActiveProfile()
        throws Exception
    {

        File f = getFileForClasspathResource( "withActiveByDefaultProfile-pom.xml" );
        MavenProject projectToClone = getProject( f );
        List<Profile> activeProfilesOrig = projectToClone.getActiveProfiles();

        assertEquals( "Expecting 1 active profile", 1, activeProfilesOrig.size() );

        MavenProject clonedProject = projectToClone.clone();

        List<Profile> activeProfilesClone = clonedProject.getActiveProfiles();

        assertEquals( "Expecting 1 active profile", 1, activeProfilesClone.size() );

        assertNotSame( "The list of active profiles should have been cloned too but is same", activeProfilesOrig,
                       activeProfilesClone );
    }
