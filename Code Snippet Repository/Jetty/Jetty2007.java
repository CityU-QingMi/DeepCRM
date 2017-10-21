    public Plugin getPlugin()
    {
        if (_plugin == null)
        {
            List plugins = _project.getBuildPlugins();
            if (plugins == null)
                return null;


            Iterator itor = plugins.iterator();
            while (itor.hasNext() && _plugin==null)
            {
                Plugin plugin = (Plugin)itor.next();
                if ("maven-war-plugin".equals(plugin.getArtifactId()))
                    _plugin = plugin;
            }
        }
        return _plugin;
    }
