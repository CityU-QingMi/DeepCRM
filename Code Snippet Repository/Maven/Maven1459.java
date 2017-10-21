    private void injectDependencyDefaults( List<Dependency> dependencies )
    {
        for ( Dependency dependency : dependencies )
        {
            if ( StringUtils.isEmpty( dependency.getScope() ) )
            {
                // we cannot set this directly in the MDO due to the interactions with dependency management
                dependency.setScope( "compile" );
            }
        }
    }
