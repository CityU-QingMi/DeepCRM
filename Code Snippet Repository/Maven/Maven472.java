    private void afterSessionEnd( Collection<MavenProject> projects, MavenSession session )
        throws MavenExecutionException
    {
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try
        {
            for ( AbstractMavenLifecycleParticipant listener : getLifecycleParticipants( projects ) )
            {
                Thread.currentThread().setContextClassLoader( listener.getClass().getClassLoader() );

                listener.afterSessionEnd( session );
            }
        }
        finally
        {
            Thread.currentThread().setContextClassLoader( originalClassLoader );
        }
    }
