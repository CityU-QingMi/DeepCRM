    public void testThatPluginDependencyThatHasSystemScopeIsResolved()
        throws Exception
    {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/

        MavenSession session = createMavenSession( getProject( "project-contributing-system-scope-plugin-dep" ) );
        MavenProject project = session.getCurrentProject();
        Plugin plugin = project.getPlugin( "org.apache.maven.its.plugins:maven-it-plugin" );

        RepositoryRequest repositoryRequest = new DefaultRepositoryRequest();
        repositoryRequest.setLocalRepository( getLocalRepository() );
        repositoryRequest.setRemoteRepositories( getPluginArtifactRepositories() );

        PluginDescriptor pluginDescriptor =
            pluginManager.loadPlugin( plugin, session.getCurrentProject().getRemotePluginRepositories(),
                                      session.getRepositorySession() );
        pluginManager.getPluginRealm( session, pluginDescriptor );
        List<Artifact> artifacts = pluginDescriptor.getArtifacts();

        for ( Artifact a : artifacts )
        {
            if ( a.getGroupId().equals( "org.apache.maven.its.mng3586" ) && a.getArtifactId().equals( "tools" ) )
            {
                // The system scoped dependencies will be present in the classloader for the plugin
                return;
            }
        }

        fail( "Can't find the system scoped dependency in the plugin artifacts." );
    }
