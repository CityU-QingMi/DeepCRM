    public static int pluginHashCode( Plugin plugin )
    {
        int hash = 17;

        hash = hash * 31 + hash( plugin.getGroupId() );
        hash = hash * 31 + hash( plugin.getArtifactId() );
        hash = hash * 31 + hash( plugin.getVersion() );

        hash = hash * 31 + ( plugin.isExtensions() ? 1 : 0 );

        for ( Dependency dependency : plugin.getDependencies() )
        {
            hash = hash * 31 + hash( dependency.getGroupId() );
            hash = hash * 31 + hash( dependency.getArtifactId() );
            hash = hash * 31 + hash( dependency.getVersion() );
            hash = hash * 31 + hash( dependency.getType() );
            hash = hash * 31 + hash( dependency.getClassifier() );
            hash = hash * 31 + hash( dependency.getScope() );

            for ( Exclusion exclusion : dependency.getExclusions() )
            {
                hash = hash * 31 + hash( exclusion.getGroupId() );
                hash = hash * 31 + hash( exclusion.getArtifactId() );
            }
        }

        return hash;
    }
