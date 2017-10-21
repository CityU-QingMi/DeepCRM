        @Override
        public boolean equals( Object o )
        {
            if ( o == this )
            {
                return true;
            }

            if ( !( o instanceof CacheKey ) )
            {
                return false;
            }

            CacheKey that = (CacheKey) o;

            return eq( groupId, that.groupId ) && eq( artifactId, that.artifactId ) && eq( version, that.version ) 
                && eq( dependencyArtifacts, that.dependencyArtifacts )
                && eq( workspace, that.workspace ) && eq( localRepo, that.localRepo ) 
                && CacheUtils.repositoriesEquals( repositories, that.repositories ) && eq( collect, that.collect ) 
                && eq( resolve, that.resolve ) && aggregating == that.aggregating;
        }
