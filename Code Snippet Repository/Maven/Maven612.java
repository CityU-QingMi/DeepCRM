    @Override
    public MavenExecutionRequest setMirrors( List<Mirror> mirrors )
    {
        if ( mirrors != null )
        {
            this.mirrors = new ArrayList<>( mirrors );
        }
        else
        {
            this.mirrors = null;
        }

        return this;
    }
