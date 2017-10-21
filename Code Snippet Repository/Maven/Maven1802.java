        public Record( String version, ArtifactRepository repository )
        {
            this.version = version;
            if ( repository != null )
            {
                repoId = repository.getId();
                repoClass = repository.getClass();
            }
            else
            {
                repoId = null;
                repoClass = null;
            }
        }
