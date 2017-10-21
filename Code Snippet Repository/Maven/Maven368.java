    @Override
    public ProjectBuildingResult build( File pomFile, ProjectBuildingRequest configuration )
        throws ProjectBuildingException
    {
        ProjectBuildingResult result = super.build( pomFile, configuration );

        result.getProject().setRemoteArtifactRepositories( Collections.<ArtifactRepository> emptyList() );

        return result;
    }
