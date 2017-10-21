    public void execute() throws MojoExecutionException, MojoFailureException
    {
        getLog().info("Configuring Jetty for project: " + this.project.getName());
        if (skip)
        {
            getLog().info("Skipping Jetty start: jetty.skip==true");
            return;
        }

        if (isExcluded(execution.getMojoDescriptor().getGoal()))
        {
            getLog().info("The goal \""+execution.getMojoDescriptor().getFullGoalName()+
                          "\" has been made unavailable for this web application by an <excludedGoal> configuration.");
            return;
        }
        
        configurePluginClasspath();
        PluginLog.setLog(getLog());
        checkPomConfiguration();
        startJetty();
    }
