    @Override
    public void mergeDuplicates( Model model, ModelBuildingRequest request, ModelProblemCollector problems )
    {
        Build build = model.getBuild();
        if ( build != null )
        {
            List<Plugin> plugins = build.getPlugins();
            Map<Object, Plugin> normalized = new LinkedHashMap<>( plugins.size() * 2 );

            for ( Plugin plugin : plugins )
            {
                Object key = plugin.getKey();
                Plugin first = normalized.get( key );
                if ( first != null )
                {
                    merger.mergePlugin( plugin, first );
                }
                normalized.put( key, plugin );
            }

            if ( plugins.size() != normalized.size() )
            {
                build.setPlugins( new ArrayList<>( normalized.values() ) );
            }
        }

/**/
/**/
/**/
/**/
/**/
/**/
/**/
        List<Dependency> dependencies = model.getDependencies();
        Map<String, Dependency> normalized = new LinkedHashMap<>( dependencies.size() * 2 );

        for ( Dependency dependency : dependencies )
        {
            normalized.put( dependency.getManagementKey(), dependency );
        }

        if ( dependencies.size() != normalized.size() )
        {
            model.setDependencies( new ArrayList<>( normalized.values() ) );
        }
    }
