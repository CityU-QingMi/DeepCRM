    @SuppressWarnings( "" )
    public ArtifactNotFoundException( String message, String groupId, String artifactId, String version, String type,
                                      String classifier, List<ArtifactRepository> remoteRepositories,
                                      String downloadUrl, List<String> path, Throwable cause )
    {
        super( constructMissingArtifactMessage( message, "", groupId, artifactId, version, type, classifier,
                                                downloadUrl, path ), groupId, artifactId, version, type, classifier,
               remoteRepositories, null, cause );

        this.downloadUrl = downloadUrl;
    }
