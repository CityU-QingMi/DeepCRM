        public CacheKey( Plugin plugin, List<RemoteRepository> repositories, RepositorySystemSession session )
        {
            groupId = plugin.getGroupId();
            artifactId = plugin.getArtifactId();
            version = plugin.getVersion();

            workspace = CacheUtils.getWorkspace( session );
            localRepo = session.getLocalRepository();
            this.repositories = new ArrayList<>( repositories.size() );
            for ( RemoteRepository repository : repositories )
            {
                if ( repository.isRepositoryManager() )
                {
                    this.repositories.addAll( repository.getMirroredRepositories() );
                }
                else
                {
                    this.repositories.add( repository );
                }
            }

            int hash = 17;
            hash = hash * 31 + groupId.hashCode();
            hash = hash * 31 + artifactId.hashCode();
            hash = hash * 31 + version.hashCode();
            hash = hash * 31 + hash( workspace );
            hash = hash * 31 + localRepo.hashCode();
            hash = hash * 31 + CacheUtils.repositoriesHashCode( repositories );
            this.hashCode = hash;
        }
