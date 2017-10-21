    public ClasspathContainer add( ArtifactMetadata md )
    {
        if ( classpath == null )
        {
            classpath = new ArrayList<>( 16 );
        }

        classpath.add( md );

        return this;
    }
