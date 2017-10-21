    @Override
    public void expandPluginConfiguration( Model model, ModelBuildingRequest request, ModelProblemCollector problems )
    {
        Build build = model.getBuild();

        if ( build != null )
        {
            expand( build.getPlugins() );

            PluginManagement pluginManagement = build.getPluginManagement();

            if ( pluginManagement != null )
            {
                expand( pluginManagement.getPlugins() );
            }
        }
    }
