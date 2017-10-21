    public void merge( PersistedToolchains dominant, PersistedToolchains recessive, String recessiveSourceLevel )
    {
        if ( dominant == null || recessive == null )
        {
            return;
        }

        recessive.setSourceLevel( recessiveSourceLevel );

        shallowMerge( dominant.getToolchains(), recessive.getToolchains(), recessiveSourceLevel );
    }
