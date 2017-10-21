    private static int getBuildNumber( Metadata metadata )
    {
        int number = 0;

        Versioning versioning = metadata.getVersioning();
        if ( versioning != null )
        {
            Snapshot snapshot = versioning.getSnapshot();
            if ( snapshot != null && snapshot.getBuildNumber() > 0 )
            {
                number = snapshot.getBuildNumber();
            }
        }

        return number;
    }
