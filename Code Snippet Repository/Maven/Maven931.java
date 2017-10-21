    public String getName()
    {
        // TODO this should not be allowed to be null.
        if ( getModel().getName() != null )
        {
            return getModel().getName();
        }
        else
        {
            return getArtifactId();
        }
    }
