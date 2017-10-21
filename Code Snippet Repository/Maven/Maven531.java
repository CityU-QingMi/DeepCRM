    public List<String> getDependencyTrail()
        throws OverConstrainedVersionException
    {
        List<Artifact> trial = getTrail();

        List<String> ret = new ArrayList<>( trial.size() );

        for ( Artifact artifact : trial )
        {
            ret.add( artifact.getId() );
        }

        return ret;
    }
