    protected static PluginDescriptor clone( PluginDescriptor original )
    {
        PluginDescriptor clone = null;

        if ( original != null )
        {
            clone = new PluginDescriptor();

            clone.setGroupId( original.getGroupId() );
            clone.setArtifactId( original.getArtifactId() );
            clone.setVersion( original.getVersion() );
            clone.setGoalPrefix( original.getGoalPrefix() );
            clone.setInheritedByDefault( original.isInheritedByDefault() );

            clone.setName( original.getName() );
            clone.setDescription( original.getDescription() );
            clone.setRequiredMavenVersion( original.getRequiredMavenVersion() );

            clone.setPluginArtifact( ArtifactUtils.copyArtifactSafe( original.getPluginArtifact() ) );

            clone.setComponents( clone( original.getMojos(), clone ) );
            clone.setId( original.getId() );
            clone.setIsolatedRealm( original.isIsolatedRealm() );
            clone.setSource( original.getSource() );

            clone.setDependencies( original.getDependencies() );
        }

        return clone;
    }
