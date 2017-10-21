    private void collectDependencyRequirements( Set<String> scopesToResolve, Set<String> scopesToCollect,
                                                Collection<MojoExecution> mojoExecutions )
    {
        for ( MojoExecution mojoExecution : mojoExecutions )
        {
            MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();

            scopesToResolve.addAll( toScopes( mojoDescriptor.getDependencyResolutionRequired() ) );

            scopesToCollect.addAll( toScopes( mojoDescriptor.getDependencyCollectionRequired() ) );
        }
    }
