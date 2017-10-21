    public void omitForNearer( Artifact omitted, Artifact kept )
    {
        String omittedVersion = omitted.getVersion();
        String keptVersion = kept.getVersion();

        if ( omittedVersion != null ? !omittedVersion.equals( keptVersion ) : keptVersion != null )
        {
            logger.debug( indent + omitted + " (removed - nearer found: " + keptVersion + ")" );
        }
    }
