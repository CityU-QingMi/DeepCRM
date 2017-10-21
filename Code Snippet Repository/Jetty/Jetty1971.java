    private List<Artifact> getWarArtifacts ()
    {
        if (warArtifacts != null)
            return warArtifacts;       
        
        warArtifacts = new ArrayList<>();
        for ( Iterator<Artifact> iter = projectArtifacts.iterator(); iter.hasNext(); )
        {
            Artifact artifact = iter.next();
            if (artifact.getType().equals("war") || artifact.getType().equals("zip"))
            {
                try
                {                  
                    warArtifacts.add(artifact);
                    getLog().info("Dependent war artifact "+artifact.getId());
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        return warArtifacts;
    }
