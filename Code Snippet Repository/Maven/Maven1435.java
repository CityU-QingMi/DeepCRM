        public void mergeManagedBuildPlugins( Model model )
        {
            Build build = model.getBuild();
            if ( build != null )
            {
                PluginManagement pluginManagement = build.getPluginManagement();
                if ( pluginManagement != null )
                {
                    mergePluginContainer_Plugins( build, pluginManagement );
                }
            }
        }
