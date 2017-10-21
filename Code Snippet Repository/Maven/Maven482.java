    public File findArtifact( Artifact artifact )
    {
        String projectKey = ArtifactUtils.key( artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion() );

        MavenProject project = projectsByGAV.get( projectKey );

        if ( project != null )
        {
            File file = find( project, artifact );
            if ( file == null && project != project.getExecutionProject() )
            {
                file = find( project.getExecutionProject(), artifact );
            }
            return file;
        }

        return null;
    }
