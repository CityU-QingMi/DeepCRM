    private void fixTimestamp( File metadataFile, Metadata metadata, Metadata reference )
    {
        boolean changed = false;

        if ( metadata != null && reference != null )
        {
            Versioning versioning = metadata.getVersioning();
            Versioning versioningRef = reference.getVersioning();
            if ( versioning != null && versioningRef != null )
            {
                String lastUpdated = versioning.getLastUpdated();
                String now = versioningRef.getLastUpdated();
                if ( lastUpdated != null && now != null && now.compareTo( lastUpdated ) < 0 )
                {
                    getLogger().warn(
                        "The last updated timestamp in " + metadataFile + " refers to the future (now = " + now
                            + ", lastUpdated = " + lastUpdated + "). Please verify that the clocks of all"
                            + " deploying machines are reasonably synchronized." );
                    versioning.setLastUpdated( now );
                    changed = true;
                }
            }
        }

        if ( changed )
        {
            getLogger().debug( "Repairing metadata in " + metadataFile );

            try ( Writer writer = WriterFactory.newXmlWriter( metadataFile ) )
            {
                new MetadataXpp3Writer().write( writer, metadata );
            }
            catch ( IOException e )
            {
                String msg = "Could not write fixed metadata to " + metadataFile + ": " + e.getMessage();
                if ( getLogger().isDebugEnabled() )
                {
                    getLogger().warn( msg, e );
                }
                else
                {
                    getLogger().warn( msg );
                }
            }
        }
    }
