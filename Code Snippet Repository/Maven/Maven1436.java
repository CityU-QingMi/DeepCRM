        private void mergePluginContainer_Plugins( PluginContainer target, PluginContainer source )
        {
            List<Plugin> src = source.getPlugins();
            if ( !src.isEmpty() )
            {
                List<Plugin> tgt = target.getPlugins();

                Map<Object, Plugin> managedPlugins = new LinkedHashMap<>( src.size() * 2 );

                Map<Object, Object> context = Collections.emptyMap();

                for ( Plugin element : src )
                {
                    Object key = getPluginKey( element );
                    managedPlugins.put( key, element );
                }

                for ( Plugin element : tgt )
                {
                    Object key = getPluginKey( element );
                    Plugin managedPlugin = managedPlugins.get( key );
                    if ( managedPlugin != null )
                    {
                        mergePlugin( element, managedPlugin, false, context );
                    }
                }
            }
        }
