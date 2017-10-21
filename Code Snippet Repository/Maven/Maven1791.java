        public Key( String groupId, String artifactId, String version, String tag )
        {
            this.groupId = groupId;
            this.artifactId = artifactId;
            this.version = version;
            this.tag = tag;

            int h = 17;
            h = h * 31 + this.groupId.hashCode();
            h = h * 31 + this.artifactId.hashCode();
            h = h * 31 + this.version.hashCode();
            h = h * 31 + this.tag.hashCode();
            hash = h;
        }
