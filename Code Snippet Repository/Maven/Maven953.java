        public CacheKey( Artifact artifact, boolean resolveManagedVersions, ArtifactRepository localRepository,
                         List<ArtifactRepository> remoteRepositories )
        {
            File file = artifact.getFile();
            this.artifact = ArtifactUtils.copyArtifact( artifact );
            if ( "pom".equals( artifact.getType() ) && file != null )
            {
                pomHash = file.getPath().hashCode() + file.lastModified();
            }
            else
            {
                pomHash = 0;
            }
            this.resolveManagedVersions = resolveManagedVersions;
            this.repositories.add( localRepository );
            this.repositories.addAll( remoteRepositories );

            int hash = 17;
            hash = hash * 31 + artifactHashCode( artifact );
            hash = hash * 31 + ( resolveManagedVersions ? 1 : 2 );
            hash = hash * 31 + repositoriesHashCode( repositories );
            this.hashCode = hash;
        }
