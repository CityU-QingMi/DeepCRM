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

            return CacheUtils.pluginEquals( plugin, that.plugin ) && eq( workspace, that.workspace )
                && eq( localRepo, that.localRepo ) && CacheUtils.repositoriesEquals( repositories, that.repositories )
                && eq( filter, that.filter );
        }
