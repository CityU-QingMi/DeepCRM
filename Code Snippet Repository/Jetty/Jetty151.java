    public List getScanTargetFiles () 
    {
        if (this.scanTargets == null)
            return null;
        
      
        FileMatchingConfiguration configuration = new FileMatchingConfiguration();
        configuration.addDirectoryScanner(scanTargets.getDirectoryScanner(project));
        return configuration.getBaseDirectories();
    }
