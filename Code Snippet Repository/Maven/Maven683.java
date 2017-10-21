    public DefaultLifecycleExecutionPlanCalculator( BuildPluginManager pluginManager,
                                                    DefaultLifecycles defaultLifeCycles,
                                                    MojoDescriptorCreator mojoDescriptorCreator,
                                                    LifecyclePluginResolver lifecyclePluginResolver )
    {
        this.pluginManager = pluginManager;
        this.defaultLifeCycles = defaultLifeCycles;
        this.mojoDescriptorCreator = mojoDescriptorCreator;
        this.lifecyclePluginResolver = lifecyclePluginResolver;
        this.mojoExecutionConfigurators =
            ImmutableMap.of( "default", (MojoExecutionConfigurator) new DefaultMojoExecutionConfigurator() );
    }
