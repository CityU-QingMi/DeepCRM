        public CacheKey( Plugin plugin, ClassLoader parentRealm, Map<String, ClassLoader> foreignImports,
                         DependencyFilter dependencyFilter, List<RemoteRepository> repositories,
                         RepositorySystemSession session )
        {
            this.plugin = plugin.clone();
            this.workspace = CacheUtils.getWorkspace( session );
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
            this.parentRealm = parentRealm;
            this.foreignImports =
                ( foreignImports != null ) ? foreignImports : Collections.<String, ClassLoader>emptyMap();
            this.filter = dependencyFilter;

            int hash = 17;
            hash = hash * 31 + CacheUtils.pluginHashCode( plugin );
            hash = hash * 31 + hash( workspace );
            hash = hash * 31 + hash( localRepo );
            hash = hash * 31 + CacheUtils.repositoriesHashCode( repositories );
            hash = hash * 31 + hash( parentRealm );
            hash = hash * 31 + this.foreignImports.hashCode();
            hash = hash * 31 + hash( dependencyFilter );
            this.hashCode = hash;
        }
