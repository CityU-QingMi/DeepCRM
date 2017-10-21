    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        if ( !"war".equals( project.getPackaging() ) || skip )
        {
            return;
        }
        warPluginInfo = new WarPluginInfo(project);
        super.execute();
    }
