    public PluginParameterExpressionEvaluator( MavenSession session, MojoExecution mojoExecution )
    {
        this.session = session;
        this.mojoExecution = mojoExecution;
        this.properties = new Properties();
        this.project = session.getCurrentProject();

        //
        // Maven4: We may want to evaluate how this is used but we add these separate as the 
        // getExecutionProperties is deprecated in MavenSession.
        //
        this.properties.putAll( session.getUserProperties() );
        this.properties.putAll( session.getSystemProperties() );
        
        String basedir = null;

        if ( project != null )
        {
            File projectFile = project.getBasedir();

            // this should always be the case for non-super POM instances...
            if ( projectFile != null )
            {
                basedir = projectFile.getAbsolutePath();
            }
        }

        if ( basedir == null )
        {
            basedir = session.getExecutionRootDirectory();
        }

        if ( basedir == null )
        {
            basedir = System.getProperty( "user.dir" );
        }

        this.basedir = basedir;
    }
