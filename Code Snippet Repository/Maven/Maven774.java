    public void injectLifecycleBindings( Model model, ModelBuildingRequest request, ModelProblemCollector problems )
    {
        String packaging = model.getPackaging();

        Collection<Plugin> defaultPlugins = lifecycle.getPluginsBoundByDefaultToAllLifecycles( packaging );

        if ( defaultPlugins == null )
        {
            problems.add( new ModelProblemCollectorRequest( Severity.ERROR, Version.BASE )
                    .setMessage( "Unknown packaging: " + packaging )
                    .setLocation( model.getLocation( "packaging" ) ) );
        }
        else if ( !defaultPlugins.isEmpty() )
        {
            Model lifecycleModel = new Model();
            lifecycleModel.setBuild( new Build() );
            lifecycleModel.getBuild().getPlugins().addAll( defaultPlugins );

            merger.merge( model, lifecycleModel );
        }
    }
