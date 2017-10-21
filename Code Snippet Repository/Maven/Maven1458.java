    @Override
    public void injectDefaultValues( Model model, ModelBuildingRequest request, ModelProblemCollector problems )
    {
        injectDependencyDefaults( model.getDependencies() );

        Build build = model.getBuild();
        if ( build != null )
        {
            for ( Plugin plugin : build.getPlugins() )
            {
                injectDependencyDefaults( plugin.getDependencies() );
            }
        }
    }
