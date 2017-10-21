    public boolean isMavenVersion( String versionRange )
    {
        VersionScheme versionScheme = new GenericVersionScheme();

        Validate.notBlank( versionRange, "versionRange can neither be null, empty nor blank" );

        VersionConstraint constraint;
        try
        {
            constraint = versionScheme.parseVersionConstraint( versionRange );
        }
        catch ( InvalidVersionSpecificationException e )
        {
            throw new IllegalArgumentException( e.getMessage(), e );
        }

        Version current;
        try
        {
            String mavenVersion = getMavenVersion();
            Validate.validState( StringUtils.isNotEmpty( mavenVersion ), "Could not determine current Maven version" );

            current = versionScheme.parseVersion( mavenVersion );
        }
        catch ( InvalidVersionSpecificationException e )
        {
            throw new IllegalStateException( "Could not parse current Maven version: " + e.getMessage(), e );
        }

        if ( constraint.getRange() == null )
        {
            return constraint.getVersion().compareTo( current ) <= 0;
        }
        return constraint.containsVersion( current );
    }
