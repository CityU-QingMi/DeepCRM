    public void endProcessChildren( Artifact artifact )
    {
        if ( wagonProvider == artifact )
        {
            wagonProvider = null;
        }
        else if ( coreArtifacts.peek() == artifact )
        {
            coreArtifacts.removeFirst();
        }
    }
