    @Deprecated
    public List<Artifact> getRuntimeArtifacts()
    {
        List<Artifact> list = new ArrayList<>( getArtifacts().size() );

        for ( Artifact a : getArtifacts()  )
        {
            // TODO classpath check doesn't belong here - that's the other method
            if ( a.getArtifactHandler().isAddedToClasspath()
            // TODO let the scope handler deal with this
                && ( Artifact.SCOPE_COMPILE.equals( a.getScope() ) || Artifact.SCOPE_RUNTIME.equals( a.getScope() ) ) )
            {
                list.add( a );
            }
        }
        return list;
    }
