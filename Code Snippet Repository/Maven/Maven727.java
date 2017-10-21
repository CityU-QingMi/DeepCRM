    public ProjectSegment findByMavenProject( MavenProject mavenProject )
    {
        for ( ProjectSegment projectBuild : items )
        {
            if ( mavenProject.equals( projectBuild.getProject() ) )
            {
                return projectBuild;
            }
        }
        return null;
    }
