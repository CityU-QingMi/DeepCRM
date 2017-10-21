    private Collection<WeakMojoExecutionListener> getProvidedListeners()
    {
        // the same instance can be provided multiple times under different Key's
        // deduplicate instances to avoid redundant beforeXXX/afterXXX callbacks
        IdentityHashMap<WeakMojoExecutionListener, Object> listeners =
            new IdentityHashMap<>();
        for ( Object provided : getScopeState().provided.values() )
        {
            if ( provided instanceof WeakMojoExecutionListener )
            {
                listeners.put( (WeakMojoExecutionListener) provided, null );
            }
        }
        return listeners.keySet();
    }
