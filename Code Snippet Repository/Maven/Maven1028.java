        public PluginBuilder addDependency( String groupId, String artifactId, String version, String scope, String systemPath, Exclusion exclusion )
        {
            Dependency d = new Dependency();
            d.setGroupId( groupId );
            d.setArtifactId( artifactId );
            d.setVersion( version );
            d.setScope( scope );

            if ( systemPath != null && scope.equals(  Artifact.SCOPE_SYSTEM ) )
            {
                d.setSystemPath( systemPath );
            }

            if ( exclusion != null )
            {
                d.addExclusion( exclusion );
            }

            plugin.getDependencies().add( d );

            return this;
        }
