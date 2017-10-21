    public void testLocalRepositoryBasedir()
        throws Exception
    {
        File localRepoDir = new File( "" ).getAbsoluteFile();

        ArtifactRepository localRepo = repositorySystem.createLocalRepository( localRepoDir );

        String basedir = localRepo.getBasedir();

        assertFalse( basedir.endsWith( "/" ) );
        assertFalse( basedir.endsWith( "\\" ) );

        assertEquals( localRepoDir, new File( basedir ) );

        assertEquals( localRepoDir.getPath(), basedir );
    }
