        public CacheKey( List<Artifact> extensionArtifacts )
        {
            this.files = new ArrayList<>( extensionArtifacts.size() );
            this.timestamps = new ArrayList<>( extensionArtifacts.size() );
            this.sizes = new ArrayList<>( extensionArtifacts.size() );
            this.ids = new ArrayList<>( extensionArtifacts.size() );

            for ( Artifact artifact : extensionArtifacts )
            {
                File file = artifact.getFile();
                files.add( file );
                timestamps.add( ( file != null ) ? Long.valueOf( file.lastModified() ) : Long.valueOf( 0 ) );
                sizes.add( ( file != null ) ? Long.valueOf( file.length() ) : Long.valueOf( 0 ) );
                ids.add( artifact.getVersion() );
            }

            this.hashCode =
                31 * files.hashCode() + 31 * ids.hashCode() + 31 * timestamps.hashCode() + 31 * sizes.hashCode();
        }
