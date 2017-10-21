        @Override
        public boolean equals( Object obj )
        {
            if ( obj == this )
            {
                return true;
            }
            else if ( obj == null || !getClass().equals( obj.getClass() ) )
            {
                return false;
            }

            Key that = (Key) obj;
            return artifactId.equals( that.artifactId ) && groupId.equals( that.groupId ) && classifier.equals(
                that.classifier ) && extension.equals( that.extension ) && version.equals( that.version )
                && context.equals( that.context ) && localRepo.equals( that.localRepo )
                && CacheUtils.eq( workspace, that.workspace )
                && CacheUtils.repositoriesEquals( repositories, that.repositories );
        }
