    private void validate30RawProfileActivation( ModelProblemCollector problems, Activation activation,
                                                 String sourceHint, String prefix, ModelBuildingRequest request )
    {
        if ( activation == null )
        {
            return;
        }

        ActivationFile file = activation.getFile();

        if ( file != null )
        {
            String path;
            boolean missing;

            if ( StringUtils.isNotEmpty( file.getExists() ) )
            {
                path = file.getExists();
                missing = false;
            }
            else if ( StringUtils.isNotEmpty( file.getMissing() ) )
            {
                path = file.getMissing();
                missing = true;
            }
            else
            {
                return;
            }

            if ( path.contains( "${project.basedir}" ) )
            {
                addViolation( problems, Severity.WARNING, Version.V30,
                              prefix + ( missing ? ".file.missing" : ".file.exists" ), null,
                              "Failed to interpolate file location " + path + " for profile " + sourceHint
                                  + ": ${project.basedir} expression not supported during profile activation, "
                                  + "use ${basedir} instead",
                              file.getLocation( missing ? "missing" : "exists" ) );
            }
            else if ( hasProjectExpression( path ) )
            {
                addViolation( problems, Severity.WARNING, Version.V30,
                              prefix + ( missing ? ".file.missing" : ".file.exists" ), null,
                              "Failed to interpolate file location " + path + " for profile " + sourceHint
                                  + ": ${project.*} expressions are not supported during profile activation",
                              file.getLocation( missing ? "missing" : "exists" ) );
            }
        }
    }
