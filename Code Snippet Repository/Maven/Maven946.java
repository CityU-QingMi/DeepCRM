        public CacheKey( String groupId, String artifactId, String version, String tag )
        {
            this.groupId = ( groupId != null ) ? groupId : "";
            this.artifactId = ( artifactId != null ) ? artifactId : "";
            this.version = ( version != null ) ? version : "";
            this.tag = ( tag != null ) ? tag : "";

            int hash = 17;
            hash = hash * 31 + this.groupId.hashCode();
            hash = hash * 31 + this.artifactId.hashCode();
            hash = hash * 31 + this.version.hashCode();
            hash = hash * 31 + this.tag.hashCode();
            hashCode = hash;
        }
