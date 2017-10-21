        public CacheKey( Plugin plugin, DependencyFilter extensionFilter, List<RemoteRepository> repositories,
                         RepositorySystemSession session )
        {
            this.plugin = plugin.clone();
            workspace = CacheUtils.getWorkspace( session );
            this.localRepo = session.getLocalRepository();
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
            this.filter = extensionFilter;

            int hash = 17;
            hash = hash * 31 + CacheUtils.pluginHashCode( plugin );
            hash = hash * 31 + hash( workspace );
            hash = hash * 31 + hash( localRepo );
            hash = hash * 31 + CacheUtils.repositoriesHashCode( repositories );
            hash = hash * 31 + hash( extensionFilter );
            this.hashCode = hash;
        }
