    @Override
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 31 + ( buildReactor == null ? 0 : buildReactor.hashCode() );
        hash = hash * 31 + ( ideWorkspace == null ? 0 : ideWorkspace.hashCode() );
        hash = hash * 31 + ( userLocalArtifactRepository == null ? 0 : userLocalArtifactRepository.hashCode() );

        return hash;
    }
