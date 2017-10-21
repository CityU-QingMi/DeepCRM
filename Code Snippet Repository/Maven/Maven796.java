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

            return parentRealm == that.parentRealm && CacheUtils.pluginEquals( plugin, that.plugin )
                && eq( workspace, that.workspace ) && eq( localRepo, that.localRepo )
                && CacheUtils.repositoriesEquals( this.repositories, that.repositories ) && eq( filter, that.filter )
                && eq( foreignImports, that.foreignImports );
        }
