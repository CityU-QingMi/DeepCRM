    private Set<URL>  getProvidedScopeJars (Set<URL> pluginJars) throws MalformedURLException
    {
        if (!useProvidedScope)
            return Collections.emptySet();
        
        HashSet<URL> providedJars = new HashSet<>();
        
        for ( Iterator<Artifact> iter = projectArtifacts.iterator(); iter.hasNext(); )
        {                   
            Artifact artifact = iter.next();
            if (Artifact.SCOPE_PROVIDED.equals(artifact.getScope()))
            {
                //test to see if the provided artifact was amongst the plugin artifacts
                URL jar = artifact.getFile().toURI().toURL();
                if (!pluginJars.contains(jar))
                {
                    providedJars.add(jar);
                    if (getLog().isDebugEnabled()) { getLog().debug("Adding provided artifact: "+artifact);}
                }  
                else
                {
                    if (getLog().isDebugEnabled()) { getLog().debug("Skipping provided artifact: "+artifact);}
                }
            }
        }
        return providedJars;
    }
