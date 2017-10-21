        public Key( RepositorySystemSession session, VersionRequest request )
        {
            Artifact artifact = request.getArtifact();
            groupId = artifact.getGroupId();
            artifactId = artifact.getArtifactId();
            classifier = artifact.getClassifier();
            extension = artifact.getExtension();
            version = artifact.getVersion();
            localRepo = session.getLocalRepository().getBasedir();
            workspace = CacheUtils.getWorkspace( session );
            repositories = new ArrayList<>( request.getRepositories().size() );
            boolean repoMan = false;
            for ( RemoteRepository repository : request.getRepositories() )
            {
                if ( repository.isRepositoryManager() )
                {
                    repoMan = true;
                    repositories.addAll( repository.getMirroredRepositories() );
                }
                else
                {
                    repositories.add( repository );
                }
            }
            context = repoMan ? request.getRequestContext() : "";

            int hash = 17;
            hash = hash * 31 + groupId.hashCode();
            hash = hash * 31 + artifactId.hashCode();
            hash = hash * 31 + classifier.hashCode();
            hash = hash * 31 + extension.hashCode();
            hash = hash * 31 + version.hashCode();
            hash = hash * 31 + localRepo.hashCode();
            hash = hash * 31 + CacheUtils.repositoriesHashCode( repositories );
            hashCode = hash;
        }
