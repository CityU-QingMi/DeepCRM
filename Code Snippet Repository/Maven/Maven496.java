    public static RemoteRepository toRepo( ArtifactRepository repo )
    {
        RemoteRepository result = null;
        if ( repo != null )
        {
            RemoteRepository.Builder builder =
                new RemoteRepository.Builder( repo.getId(), getLayout( repo ), repo.getUrl() );
            builder.setSnapshotPolicy( toPolicy( repo.getSnapshots() ) );
            builder.setReleasePolicy( toPolicy( repo.getReleases() ) );
            builder.setAuthentication( toAuthentication( repo.getAuthentication() ) );
            builder.setProxy( toProxy( repo.getProxy() ) );
            builder.setMirroredRepositories( toRepos( repo.getMirroredRepositories() ) );
            result = builder.build();
        }
        return result;
    }
