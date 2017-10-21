    public void addTestResource( MavenProject project, String resourceDirectory, List<String> includes,
                                 List<String> excludes )
    {
        Resource resource = new Resource();
        resource.setDirectory( resourceDirectory );
        resource.setIncludes( includes );
        resource.setExcludes( excludes );

        project.addTestResource( resource );
    }
