        public void addArtifact( ArtifactSpec spec )
        {
            artifacts.put( getKey( spec.artifact ), spec );

            String key = spec.artifact.getDependencyConflictId();
            List<ArtifactVersion> artifactVersions = versions.get( key );
            if ( artifactVersions == null )
            {
                artifactVersions = new ArrayList<>();
                versions.put( key, artifactVersions );
            }
            if ( spec.artifact.getVersion() != null )
            {
                artifactVersions.add( new DefaultArtifactVersion( spec.artifact.getVersion() ) );
            }
        }
