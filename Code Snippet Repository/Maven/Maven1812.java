    @Override
    protected void merge( Metadata recessive )
    {
        metadata.getVersioning().updateTimestamp();

        if ( !legacyFormat )
        {
            String lastUpdated = metadata.getVersioning().getLastUpdated();

            Map<String, SnapshotVersion> versions = new LinkedHashMap<>();

            for ( Artifact artifact : artifacts )
            {
                SnapshotVersion sv = new SnapshotVersion();
                sv.setClassifier( artifact.getClassifier() );
                sv.setExtension( artifact.getExtension() );
                sv.setVersion( getVersion() );
                sv.setUpdated( lastUpdated );
                versions.put( getKey( sv.getClassifier(), sv.getExtension() ), sv );
            }

            Versioning versioning = recessive.getVersioning();
            if ( versioning != null )
            {
                for ( SnapshotVersion sv : versioning.getSnapshotVersions() )
                {
                    String key = getKey( sv.getClassifier(), sv.getExtension() );
                    if ( !versions.containsKey( key ) )
                    {
                        versions.put( key, sv );
                    }
                }
            }

            metadata.getVersioning().setSnapshotVersions( new ArrayList<>( versions.values() ) );
        }

        artifacts.clear();
    }
