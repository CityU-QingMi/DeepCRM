    @Override
    protected void merge( Metadata recessive )
    {
        Versioning versioning = metadata.getVersioning();
        versioning.updateTimestamp();

        if ( recessive.getVersioning() != null )
        {
            if ( versioning.getLatest() == null )
            {
                versioning.setLatest( recessive.getVersioning().getLatest() );
            }
            if ( versioning.getRelease() == null )
            {
                versioning.setRelease( recessive.getVersioning().getRelease() );
            }

            Collection<String> versions = new LinkedHashSet<>( recessive.getVersioning().getVersions() );
            versions.addAll( versioning.getVersions() );
            versioning.setVersions( new ArrayList<>( versions ) );
        }
    }
