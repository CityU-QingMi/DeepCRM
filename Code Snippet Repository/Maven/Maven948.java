        public CacheKey( String groupId, String artifactId, String version )
        {
            this.groupId = ( groupId != null ) ? groupId : "";
            this.artifactId = ( artifactId != null ) ? artifactId : "";
            this.version = ( version != null ) ? version : "";

            int hash = 17;
            hash = hash * 31 + this.groupId.hashCode();
            hash = hash * 31 + this.artifactId.hashCode();
            hash = hash * 31 + this.version.hashCode();
            hashCode = hash;
        }
