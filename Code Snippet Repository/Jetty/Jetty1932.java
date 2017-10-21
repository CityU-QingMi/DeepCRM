    private Set<URL> getPluginJars () throws MalformedURLException
    {
        HashSet<URL> pluginJars = new HashSet<>();
        for (Iterator<Artifact> iter = pluginArtifacts.iterator(); iter.hasNext(); )
        {
            Artifact pluginArtifact = iter.next();
            if ("jar".equalsIgnoreCase(pluginArtifact.getType()))
            {
                if (getLog().isDebugEnabled()) { getLog().debug("Adding plugin artifact "+pluginArtifact);}
                pluginJars.add(pluginArtifact.getFile().toURI().toURL());
            }
        }
        
        return pluginJars;
    }
