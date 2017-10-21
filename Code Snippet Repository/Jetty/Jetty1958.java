    private List<Artifact> getWarArtifacts()
    throws MalformedURLException, IOException
    {
        List<Artifact> warArtifacts = new ArrayList<Artifact>();
        for ( Iterator<Artifact> iter = project.getArtifacts().iterator(); iter.hasNext(); )
        {
            Artifact artifact = iter.next();
            
            if (artifact.getType().equals("war"))
                warArtifacts.add(artifact);
        }

        return warArtifacts;
    }
