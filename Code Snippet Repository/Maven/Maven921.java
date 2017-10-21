    @Deprecated
    public List<Artifact> getSystemArtifacts()
    {
        List<Artifact> list = new ArrayList<>( getArtifacts().size() );

        for ( Artifact a : getArtifacts() )
        {
            // TODO classpath check doesn't belong here - that's the other method
            if ( a.getArtifactHandler().isAddedToClasspath() )
            {
                // TODO let the scope handler deal with this
                if ( Artifact.SCOPE_SYSTEM.equals( a.getScope() ) )
                {
                    list.add( a );
                }
            }
        }
        return list;
    }
