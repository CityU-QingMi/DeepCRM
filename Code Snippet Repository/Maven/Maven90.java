    public static ArtifactStatus valueOf( String status )
    {
        ArtifactStatus retVal = null;

        if ( status != null )
        {
            retVal = map.get( status );
        }

        return retVal != null ? retVal : NONE;
    }
